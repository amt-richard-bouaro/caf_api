package com.amalitech.caf.controllers;

import com.amalitech.caf.dtos.global.SuccessResponse;
import com.amalitech.caf.dtos.stadium.NewStadium;
import com.amalitech.caf.dtos.stadium.StadiumDto;
import com.amalitech.caf.entities.StadiumEntity;
import com.amalitech.caf.enums.ResponseStatus;
import com.amalitech.caf.mappers.StadiumMapper;
import com.amalitech.caf.services.StadiumService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@RestController
@RequestMapping(path = "/api/v1/stadia")
@RequiredArgsConstructor
@Tag(name = "Stadia")
public class StadiumController {
    private final StadiumService stadiumService;
    private final StadiumMapper stadiumMapper;

    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SuccessResponse<StadiumDto>> createStadium(@NotBlank(message = "City name is required") String city,
                                                                     MultipartFile image,
                                                                     @NotBlank(message = "Name is required") String name,
                                                                     Long capacity,
                                                                     Long host

    ) {
        NewStadium payload = NewStadium.builder()
                .host(host)
                .city(city)
                .image(image)
                .name(name)
                .capacity(capacity)
                .build();
        StadiumEntity createdStadium = stadiumService.createStadium(payload);

        StadiumDto responseData = stadiumMapper.mapFromEntityToDto(createdStadium);
        SuccessResponse<StadiumDto> res = new SuccessResponse<>(ResponseStatus.SUCCESS, "Created successfully", Instant.now().toString(), responseData);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
