package com.medsync.medsync.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.medsync.medsync.dto.PatientDTO;
import com.medsync.medsync.mapper.PatientMapper;
import com.medsync.medsync.model.Patient;
import com.medsync.medsync.repository.PatientRepository;

import jakarta.annotation.Nonnull;

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

    public Patient saveFromDto(@Nonnull PatientDTO dto) {
        @Nonnull
        Patient patient = Objects.requireNonNull(patientMapper.toEntity(dto), "Mapped patient entity cannot be null");
        return patientRepository.save(patient);
    }

    public PatientDTO getPatientById(@Nonnull long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Patient with ID " + id + " not found"));
    }

    public List<PatientDTO> searchByLastName(String lastName) {
    return patientRepository.findByLastNameContainingIgnoreCase(lastName)
            .stream()
            .map(patientMapper::toDto)
            .toList(); 
}
}