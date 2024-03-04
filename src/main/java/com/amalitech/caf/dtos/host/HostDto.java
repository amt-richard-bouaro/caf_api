package com.amalitech.caf.dtos.host;

import com.amalitech.caf.dtos.stadium.StadiumDto;
import com.amalitech.caf.entities.StadiumEntity;
import com.amalitech.caf.entities.TournamentEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class HostDto {
    private Long id;

    private String country;

    private List<StadiumDto> cities = new ArrayList<>();

//    private TournamentEntity tournament;
}
