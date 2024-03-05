package com.amalitech.caf.dtos.stadium;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class StadiumDto {
    private Long id;

    private String city;

    private String image;


    private String name;


    private Long capacity;
}
