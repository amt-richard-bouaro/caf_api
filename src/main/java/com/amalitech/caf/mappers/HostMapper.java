package com.amalitech.caf.mappers;

import com.amalitech.caf.configs.mapping.Mapper;
import com.amalitech.caf.dtos.host.HostDto;
import com.amalitech.caf.entities.HostEntity;

public interface HostMapper {
    HostEntity mapFromDtoToEntity(HostDto hostDto);

    HostDto mapFromEntityToDto(HostEntity hostEntity);
}
