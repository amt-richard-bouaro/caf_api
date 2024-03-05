package com.amalitech.caf.mappers;

import com.amalitech.caf.configs.mapping.Mapper;
import com.amalitech.caf.dtos.auth.NewUserPayload;
import com.amalitech.caf.entities.UserEntity;

public interface UserMapper extends Mapper<UserEntity, NewUserPayload> {
    UserEntity mapFromDtoToEntity(NewUserPayload userDTO);

    NewUserPayload mapFromEntityToDto(UserEntity userEntity);
}
