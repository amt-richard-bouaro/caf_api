package com.amalitech.caf.mappers.impl;

import com.amalitech.caf.dtos.stadium.StadiumResponse;
import com.amalitech.caf.entities.StadiumEntity;
import com.amalitech.caf.mappers.StadiumMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StadiumMapperImp implements StadiumMapper {
    private final ModelMapper modelMapper;

    public StadiumMapperImp(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public StadiumResponse mapFromEntityToDto(StadiumEntity stadiumEntity) {
        return modelMapper.map(stadiumEntity, StadiumResponse.class);
    }

    @Override
    public StadiumEntity mapFromDtoToEntity(StadiumResponse stadiumResponse) {
        return modelMapper.map(stadiumResponse, StadiumEntity.class);
    }
}
