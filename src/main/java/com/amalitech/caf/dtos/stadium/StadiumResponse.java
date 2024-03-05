package com.amalitech.caf.dtos.stadium;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class StadiumResponse {
    private Long id;

    private String city;

    private String image;


    private String name;


    private Long capacity;
}
