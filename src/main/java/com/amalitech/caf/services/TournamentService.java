package com.amalitech.caf.services;

import com.amalitech.caf.dtos.entities.TournamentDto;
import com.amalitech.caf.entities.HostEntity;
import com.amalitech.caf.entities.TournamentEntity;

import java.util.List;

public interface TournamentService {
    TournamentEntity createNewTournament(
            TournamentEntity tournamentEntity
    );
}
