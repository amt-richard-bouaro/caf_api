package com.amalitech.caf.mappers;

import com.amalitech.caf.dtos.stadium.StadiumDto;
import com.amalitech.caf.entities.StadiumEntity;

public interface StadiumMapper {
    StadiumDto mapFromEntityToDto(StadiumEntity stadiumEntity);

    StadiumEntity mapFromDtoToEntity(StadiumDto stadiumDto);
}