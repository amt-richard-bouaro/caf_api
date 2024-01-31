package com.amalitech.caf.models.mappers;

import com.amalitech.caf.interfaces.MapperInterface;
import com.amalitech.caf.models.dtos.UserDTO;
import com.amalitech.caf.models.entities.UserEntity;
import org.modelmapper.ModelMapper;

public class UserMapper implements MapperInterface<UserEntity, UserDTO> {

    private final ModelMapper modelMapper;

    public UserMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserEntity mapFromDtoToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, UserEntity.class);
    }

    @Override
    public UserDTO mapFromEntityToDto(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDTO.class);
    }
}
