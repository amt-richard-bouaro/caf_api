package com.amalitech.caf.mappers;

import com.amalitech.caf.configs.mapping.Mapper;
import com.amalitech.caf.dtos.entities.TournamentDto;
import com.amalitech.caf.entities.TournamentEntity;

public interface TournamentMapper {
    TournamentEntity mapFromDtoToEntity(TournamentDto tournamentDto);
    
    TournamentDto mapFromEntityToDto(TournamentEntity tournamentEntity);
}
