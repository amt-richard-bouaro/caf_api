package com.amalitech.caf.services;

import com.amalitech.caf.dtos.host.HostRequest;
import com.amalitech.caf.entities.HostEntity;
import com.amalitech.caf.entities.StadiumEntity;

public interface HostService {
    HostEntity createHost(
            HostRequest payload
    );

    HostEntity getHost(Long id);

    HostEntity updateHostCities(StadiumEntity payload);
}
