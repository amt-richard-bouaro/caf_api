package com.amalitech.caf.services.Impl;

import com.amalitech.caf.dtos.stadium.NewStadium;
import com.amalitech.caf.entities.StadiumEntity;
import com.amalitech.caf.exceptions.ConflictException;
import com.amalitech.caf.mappers.StadiumMapper;
import com.amalitech.caf.repositories.StadiumRepository;
import com.amalitech.caf.services.CloudinaryService;
import com.amalitech.caf.services.StadiumService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
@Slf4j
public class StadiumServiceImp implements StadiumService {

    private final StadiumRepository stadiumRepository;

    private final StadiumMapper stadiumMapper;

    private CloudinaryService cloudinaryService;

    @Override
    public StadiumEntity createStadium(NewStadium payload) {

        List<StadiumEntity> existingStadia = stadiumRepository.findStadiumByCityOrName(payload.getName(), payload.getCity());

        if (existingStadia.isEmpty()) {
            throw new ConflictException("Stadium already exists");
        }

        String imageUrl = cloudinaryService.uploadFile(payload.getImage(), "stadia");

        StadiumEntity stadium = StadiumEntity.builder()
                .image(imageUrl)
                .city(payload.getCity())
                .capacity(payload.getCapacity())
                .name(payload.getName())
                .build();

        return stadiumRepository.save(stadium);

    }
}
