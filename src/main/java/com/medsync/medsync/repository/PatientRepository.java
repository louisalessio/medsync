package com.medsync.medsync.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medsync.medsync.model.Patient;

//here we autogenerate the queries
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByLastNameContainingIgnoreCase(String lastName);

    boolean existsByEmail(String email);
}