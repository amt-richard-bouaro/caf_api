package com.amalitech.caf.mappers;

import com.amalitech.caf.dtos.tournament.NewTournamentDto;
import com.amalitech.caf.dtos.tournament.TournamentDto;
import com.amalitech.caf.entities.TournamentEntity;

public interface TournamentMapper {
    TournamentEntity mapFromDtoToEntity(NewTournamentDto tournamentDto);

    TournamentDto mapFromEntityToDto(TournamentEntity tournamentEntity);
}
