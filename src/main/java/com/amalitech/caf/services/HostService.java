package com.amalitech.caf.services;

import com.amalitech.caf.dtos.host.NewHostPayloadDto;
import com.amalitech.caf.entities.HostEntity;
import com.amalitech.caf.entities.StadiumEntity;

public interface HostService {
    HostEntity createHost(
            NewHostPayloadDto payload
    );

    HostEntity getHost(Long id);

    HostEntity updateHostCities(StadiumEntity payload);
}
