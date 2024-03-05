package com.amalitech.caf.services.Impl;

import com.amalitech.caf.dtos.stadium.StadiumRequest;
import com.amalitech.caf.entities.HostEntity;
import com.amalitech.caf.entities.StadiumEntity;
import com.amalitech.caf.exceptions.ConflictException;
import com.amalitech.caf.mappers.StadiumMapper;
import com.amalitech.caf.repositories.StadiumRepository;
import com.amalitech.caf.services.CloudinaryService;
import com.amalitech.caf.services.HostService;
import com.amalitech.caf.services.StadiumService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StadiumServiceImp implements StadiumService {

    private final StadiumRepository stadiumRepository;

    private final HostService hostService;

    private final StadiumMapper stadiumMapper;

    private CloudinaryService cloudinaryService;

    @Override
    public StadiumEntity createStadium(StadiumRequest payload) {

        List<StadiumEntity> existingStadia = stadiumRepository.findStadiumByCityOrName(payload.getName(), payload.getCity());

        if (!existingStadia.isEmpty()) {
            throw new ConflictException("Stadium already exists");
        }

        HostEntity host = hostService.getHost(payload.getHost());

        String imageUrl = cloudinaryService.uploadFile(payload.getImage(), "stadia");

        StadiumEntity stadium = StadiumEntity.builder()
                .image(imageUrl)
                .host(host)
                .city(payload.getCity())
                .capacity(payload.getCapacity())
                .name(payload.getName())
                .build();

        StadiumEntity createdStadium = stadiumRepository.save(stadium);

        hostService.updateHostCities(createdStadium);

        return createdStadium;
    }

}