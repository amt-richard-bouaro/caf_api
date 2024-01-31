package com.amalitech.caf.interfaces;

import org.modelmapper.ModelMapper;

public interface MapperInterface<Entity, DTO> {

    Entity mapFromDtoToEntity(DTO dto);
    DTO mapFromEntityToDto(Entity entity);

}
