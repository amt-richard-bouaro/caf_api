package com.amalitech.caf.mappers;

import com.amalitech.caf.dtos.tournament.TournamentRequest;
import com.amalitech.caf.dtos.tournament.TournamentResponse;
import com.amalitech.caf.entities.TournamentEntity;

public interface TournamentMapper {
    TournamentEntity mapFromDtoToEntity(TournamentRequest tournamentDto);

    TournamentResponse mapFromEntityToDto(TournamentEntity tournamentEntity);
}
