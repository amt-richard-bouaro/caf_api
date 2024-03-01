package com.amalitech.caf.mappers.impl;

import com.amalitech.caf.dtos.host.HostDto;
import com.amalitech.caf.entities.HostEntity;
import com.amalitech.caf.mappers.HostMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HostMapperImp implements HostMapper {

    private final ModelMapper modelMapper;

    public HostMapperImp(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public HostEntity mapFromDtoToEntity(HostDto hostDto) {

        return modelMapper.map(hostDto, HostEntity.class);
    }


    @Override
    public HostDto mapFromEntityToDto(HostEntity hostEntity) {
        return modelMapper.map(hostEntity, HostDto.class);
    }
}
