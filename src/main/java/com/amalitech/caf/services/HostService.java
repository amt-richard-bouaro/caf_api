package com.amalitech.caf.services;

import com.amalitech.caf.dtos.host.NewHostPayloadDto;
import com.amalitech.caf.entities.HostEntity;

public interface HostService {
    HostEntity createHost(
            NewHostPayloadDto payload
    );
}
