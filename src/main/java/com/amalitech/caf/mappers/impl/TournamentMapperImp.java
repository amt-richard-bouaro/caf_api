package com.amalitech.caf.mappers.impl;


import com.amalitech.caf.dtos.tournament.TournamentRequest;
import com.amalitech.caf.dtos.tournament.TournamentResponse;
import com.amalitech.caf.entities.TournamentEntity;
import com.amalitech.caf.mappers.TournamentMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TournamentMapperImp implements TournamentMapper {
    private final ModelMapper modelMapper;

    public TournamentMapperImp(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TournamentEntity mapFromDtoToEntity(TournamentRequest tournamentDto) {
        return modelMapper.map(tournamentDto, TournamentEntity.class);
    }

    @Override
    public TournamentResponse mapFromEntityToDto(TournamentEntity tournamentEntity) {
        return modelMapper.map(tournamentEntity, TournamentResponse.class);
    }
}
