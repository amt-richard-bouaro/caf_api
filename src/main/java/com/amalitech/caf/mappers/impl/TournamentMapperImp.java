package com.amalitech.caf.mappers.impl;


import com.amalitech.caf.dtos.requests.NewTournamentDto;
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
    public TournamentEntity mapFromDtoToEntity(NewTournamentDto tournamentDto) {
        return modelMapper.map(tournamentDto, TournamentEntity.class);
    }

    @Override
    public NewTournamentDto mapFromEntityToDto(TournamentEntity tournamentEntity) {
        return modelMapper.map(tournamentEntity, NewTournamentDto.class);
    }
}
