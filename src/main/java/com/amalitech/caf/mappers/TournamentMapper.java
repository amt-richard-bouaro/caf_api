package com.amalitech.caf.mappers;

import com.amalitech.caf.dtos.requests.NewTournamentDto;
import com.amalitech.caf.entities.TournamentEntity;

public interface TournamentMapper {
    TournamentEntity mapFromDtoToEntity(NewTournamentDto tournamentDto);

    NewTournamentDto mapFromEntityToDto(TournamentEntity tournamentEntity);
}
