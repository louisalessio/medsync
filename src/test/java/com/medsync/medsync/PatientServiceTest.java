package com.medsync.medsync;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.medsync.medsync.dto.PatientDTO;
import com.medsync.medsync.mapper.PatientMapper;
import com.medsync.medsync.model.Patient;
import com.medsync.medsync.repository.PatientRepository;
import com.medsync.medsync.service.PatientService;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository; // fake repo

    @Mock
    private PatientMapper patientMapper;

    @InjectMocks
    private PatientService patientService; // real service where we put the mock

    @Test
    void whenFindById_shouldReturnPatient() {
        // initial data
        Patient mockPatient = new Patient();
        mockPatient.setId(1L);
        mockPatient.setFirstName("Louis");

        PatientDTO mockDto = new PatientDTO(); // fake DTO
        mockDto.setFirstName("Louis");

        // we tell mock what to do
        when(patientRepository.findById(1L)).thenReturn(Optional.of(mockPatient));
        when(patientMapper.toDto(mockPatient)).thenReturn(mockDto);

        // the action we test
        var result = patientService.getPatientById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo("Louis");
        verify(patientRepository, times(1)).findById(1L);
        verify(patientMapper, times(1)).toDto(mockPatient); // we verify that repo has been called
    }

    @Test
    @DisplayName("Should save patient when email is unique")
    @SuppressWarnings("null")
    void createPatient_Success() {
        // GIVEN
        PatientDTO dto = new PatientDTO();
        dto.setEmail("new@medsync.ch");

        Patient entity = new Patient();
        entity.setEmail("new@medsync.ch");

        // Stubbing
        when(patientRepository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(patientMapper.toEntity(dto)).thenReturn(entity);
        when(patientRepository.save(any(Patient.class))).thenReturn(entity);
        when(patientMapper.toDto(entity)).thenReturn(dto);

        // WHEN
        PatientDTO result = patientService.createPatient(dto);

        // THEN
        assertThat(result).isNotNull();
        verify(patientRepository).save(any(Patient.class)); 
    }

    @Test
    @SuppressWarnings("null")
    void createPatient_Fail_DuplicateEmail() {
        // GIVEN
        PatientDTO dto = new PatientDTO();
        dto.setEmail("duplicate@medsync.ch");

        // email already exists
        when(patientRepository.existsByEmail(dto.getEmail())).thenReturn(true);

        // WHEN and THEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            patientService.createPatient(dto);
        });

        assertThat(exception.getMessage()).contains("already exists");

        // verify that save was never called
        verify(patientRepository, never()).save(any(Patient.class));
    }
}