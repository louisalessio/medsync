package com.medsync.medsync.model;

import java.time.LocalDate;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "patients")
@Data
//this annotation creates a 'deleted' column (default is boolean), 
// changes 'repository.delete()' into an UPDATE 
// and automatically filters out deleted records from all queries
@SoftDelete
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private LocalDate birthDate;

    private String gender;

    @Column(unique = true)
    private String socialSecurityNumber; 

    private String phoneNumber;

    private String email;
}