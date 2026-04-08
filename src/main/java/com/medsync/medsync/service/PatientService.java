package com.medsync.medsync.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public Patient createPatientFromDTO(Patient patient) {
        return patientRepository.save(patient);
    }
}