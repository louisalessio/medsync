package com.medsync.medsync.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.medsync.medsync.dto.PatientDTO;
import com.medsync.medsync.model.Patient;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    // from the DTO to a Entity for the DB
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "gender", ignore = true)
    Patient toEntity(PatientDTO dto);

    // from the Entity of the DB into a DTO for the client
    PatientDTO toDto(Patient patient);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "gender", ignore = true)
    void updateEntityFromDto(PatientDTO dto, @MappingTarget Patient entity);
}