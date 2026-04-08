package com.medsync.medsync.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medsync.medsync.dto.PatientDTO;
import com.medsync.medsync.mapper.PatientMapper;
import com.medsync.medsync.model.Patient;
import com.medsync.medsync.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient saveFromDto(PatientDTO dto) {
        Patient patient = patientMapper.toEntity(dto);
        return patientRepository.save(patient);
    }

    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return patientMapper.toDto(patient);
    }
}