package com.medsync.medsync.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medsync.medsync.dto.PatientDTO;
import com.medsync.medsync.model.Patient;
import com.medsync.medsync.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient saveFromDto(PatientDTO dto) {
        Patient patient = new Patient();
        // mapping of data
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setBirthDate(dto.getBirthDate());
        patient.setEmail(dto.getEmail());
        patient.setSocialSecurityNumber(dto.getSocialSecurityNumber());
        return patientRepository.save(patient);
    }
}