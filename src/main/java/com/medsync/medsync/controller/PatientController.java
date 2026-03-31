package com.medsync.medsync.controller;

import com.medsync.medsync.model.Patient;
import com.medsync.medsync.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Patient create(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }
}