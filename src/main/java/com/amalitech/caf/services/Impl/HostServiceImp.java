package com.amalitech.caf.services.Impl;

import com.amalitech.caf.dtos.host.NewHostPayloadDto;
import com.amalitech.caf.entities.HostEntity;
import com.amalitech.caf.mappers.HostMapper;
import com.amalitech.caf.services.HostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HostServiceImp implements HostService {

    private final HostMapper hostMapper;

    @Override
    public HostEntity createHost(NewHostPayloadDto payload) {
        return null;
    }

}
