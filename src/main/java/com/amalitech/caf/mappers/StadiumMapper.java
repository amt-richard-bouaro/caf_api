package com.amalitech.caf.mappers;

import com.amalitech.caf.dtos.stadium.StadiumResponse;
import com.amalitech.caf.entities.StadiumEntity;

public interface StadiumMapper {
    StadiumResponse mapFromEntityToDto(StadiumEntity stadiumEntity);

    StadiumEntity mapFromDtoToEntity(StadiumResponse stadiumResponse);
}