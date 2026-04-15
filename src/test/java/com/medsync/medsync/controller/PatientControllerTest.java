package com.medsync.medsync.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medsync.medsync.dto.PatientDTO;
import com.medsync.medsync.service.PatientService;

@WebMvcTest(PatientController.class) //here we test the controller
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc; //simulate HTTP calls

    @MockitoBean
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper; //transform DTO into JSON

    @Test
    @DisplayName("POST /api/patients - Should return 201 Created when valid")
    void createPatient_Success() throws Exception {
        // GIVEN
        PatientDTO inputDto = new PatientDTO();
        inputDto.setFirstName("Louis");
        inputDto.setLastName("Alessio");
        inputDto.setEmail("louis@medsync.ch");

        when(patientService.createPatient(any(PatientDTO.class))).thenReturn(inputDto);

        // WHEN & THEN
        mockMvc.perform(post(Objects.requireNonNull("/api/patients"))
                .contentType(Objects.requireNonNull(MediaType.APPLICATION_JSON))
                .contentType(Objects.requireNonNull(MediaType.APPLICATION_JSON)) //transform the obj into JSON
                .content(Objects.requireNonNull(objectMapper.writeValueAsString(inputDto))))
        .andExpect(status().isCreated());
    }
}