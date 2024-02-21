package com.amalitech.caf.mappers.impl;

import com.amalitech.caf.dtos.entities.UserDto;
import com.amalitech.caf.entities.UserEntity;
import com.amalitech.caf.mappers.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImp implements UserMapper {
    
    private final ModelMapper modelMapper;
    
    public UserMapperImp(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    
    @Override
    public UserEntity mapFromDtoToEntity(UserDto userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
    
    @Override
    public UserDto mapFromEntityToDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }
}
