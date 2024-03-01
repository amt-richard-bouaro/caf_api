package com.amalitech.caf.mappers.impl;

import com.amalitech.caf.dtos.stadium.StadiumDto;
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
    public StadiumDto mapFromEntityToDto(StadiumEntity stadiumEntity) {
        return modelMapper.map(stadiumEntity, StadiumDto.class);
    }

    @Override
    public StadiumEntity mapFromDtoToEntity(StadiumDto stadiumDto) {
        return modelMapper.map(stadiumDto, StadiumEntity.class);
    }
}
