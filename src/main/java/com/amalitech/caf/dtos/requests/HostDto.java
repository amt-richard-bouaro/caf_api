package com.amalitech.caf.dtos.requests;

import com.amalitech.caf.entities.TournamentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class HostDto {
    private Long id;

    private String country;

    private List<String> cities;

//    private TournamentEntity tournament;
}