package com.amalitech.caf.dtos.host;

import com.amalitech.caf.dtos.stadium.StadiumResponse;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class HostResponse {
    private Long id;

    private String country;

    private List<StadiumResponse> cities = new ArrayList<>();

//    private TournamentEntity tournament;
}
