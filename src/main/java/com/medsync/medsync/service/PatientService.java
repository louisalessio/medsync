package com.medsync.medsync.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medsync.medsync.dto.PatientDTO;
import com.medsync.medsync.mapper.PatientMapper;
import com.medsync.medsync.model.Patient;
import com.medsync.medsync.repository.PatientRepository;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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

    @Nonnull
    @SuppressWarnings("null")
    public PatientDTO createPatient(@Nonnull PatientDTO dto) {
        log.info("Attempting to create a new patient with email: {}", dto.getEmail());
        if (patientRepository.existsByEmail(dto.getEmail())) {
            log.error("Creazione fallita: l'email {} è già presente", dto.getEmail());
            throw new RuntimeException("Email già in uso!");
        }
        Patient patient = patientMapper.toEntity(dto);
        Patient savedPatient = patientRepository.save(patient);
        log.info("Patient successfully created with ID: {}", savedPatient.getId());
        return patientMapper.toDto(savedPatient);
    }

    public PatientDTO getPatientById(@Nonnull long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toDto)
                .orElseThrow(() -> {
                    log.warn("Search failed: Patient with ID {} not found", id);
                    return new RuntimeException("Patient not found");
                });
    }

    public List<PatientDTO> searchByLastName(String lastName) {
        return patientRepository.findByLastNameContainingIgnoreCase(lastName)
                .stream()
                .map(patientMapper::toDto)
                .toList();
    }

    // mark the patient as deleted
    public void deletePatient(@Nonnull long id) {
        log.warn("Soft-deleting patient with ID: {}", id);
        if (!patientRepository.existsById(id)) {
            log.error("Delete failed: Patient {} not found", id);
            throw new RuntimeException("Patient with ID " + id + " not found");
        }
        patientRepository.deleteById(id);
    }

    // update an existing patient
    @SuppressWarnings("null")
    @Nonnull
    public PatientDTO updatePatient(@Nonnull long id, @Nonnull PatientDTO dto) {
        log.info("Updating patient with ID: {}", id);
        // fetch the patient (or throw error if not found)
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient with ID " + id + " not found"));

        // update the existing entity with DTO data
        patientMapper.updateEntityFromDto(dto, existingPatient);

        // save and return the updated DTO
        Patient updatedPatient = patientRepository.save(existingPatient);

        log.info("Patient with ID: {} successfully updated", id);
        return patientMapper.toDto(updatedPatient);
    }
}