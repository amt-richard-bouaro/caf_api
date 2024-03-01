package com.amalitech.caf.dtos.tournament;

import com.amalitech.caf.dtos.host.NewHostPayloadDto;

import java.util.List;

public class TournamentDto {
    private Long id;
    private String name;
    private String edition;
    private List<NewHostPayloadDto> hosts;
}
