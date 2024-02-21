package com.amalitech.caf.configs.mapping;

public interface Mapper<Entity, DTO> {
    
    Entity mapFromDtoToEntity(DTO dto);
    
    DTO mapFromEntityToDto(Entity entity);
    
}
