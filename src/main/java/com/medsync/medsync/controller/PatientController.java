package com.medsync.medsync.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medsync.medsync.dto.PatientDTO;
import com.medsync.medsync.model.Patient;
import com.medsync.medsync.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAll() {
        return patientService.getAllPatients();
    }

    @PostMapping
    public Patient createPatient(@Valid @RequestBody PatientDTO patientDTO) {
        return patientService.createPatientFromDTO(patientDTO);
    }
}