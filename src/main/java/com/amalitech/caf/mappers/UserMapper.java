package com.amalitech.caf.mappers;

import com.amalitech.caf.configs.mapping.Mapper;
import com.amalitech.caf.dtos.entities.UserDto;
import com.amalitech.caf.entities.UserEntity;

public interface UserMapper extends Mapper<UserEntity, UserDto> {
    UserEntity mapFromDtoToEntity(UserDto userDTO);
    
    UserDto mapFromEntityToDto(UserEntity userEntity);
}
