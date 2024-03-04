package com.amalitech.caf.services.Impl;

import com.amalitech.caf.dtos.host.NewHostPayloadDto;
import com.amalitech.caf.entities.HostEntity;
import com.amalitech.caf.entities.StadiumEntity;
import com.amalitech.caf.exceptions.NotFoundException;
import com.amalitech.caf.mappers.HostMapper;
import com.amalitech.caf.repositories.HostRepository;
import com.amalitech.caf.services.HostService;
import lombok.AllArgsConstructor;
import org.apache.catalina.Host;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class HostServiceImp implements HostService {

    private final HostMapper hostMapper;

    private final HostRepository hostRepository;

    @Override
    public HostEntity createHost(NewHostPayloadDto payload) {
        return null;
    }

    @Override
    public HostEntity getHost(Long id) {
        return hostRepository.findById(id).orElseThrow(() -> new NotFoundException("No such host"));
    }


    @Override
    public HostEntity updateHostCities(StadiumEntity payload) {

        HostEntity host = hostRepository.findById(payload.getId()).orElseThrow(() -> new NoSuchElementException("No such host"));

        host.getCities().add(payload);
        return hostRepository.save(host);

    }

}
