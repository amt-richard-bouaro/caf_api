package com.amalitech.caf.mappers;

import com.amalitech.caf.dtos.host.HostResponse;
import com.amalitech.caf.entities.HostEntity;

public interface HostMapper {
    HostEntity mapFromDtoToEntity(HostResponse hostResponse);

    HostResponse mapFromEntityToDto(HostEntity hostEntity);
}
