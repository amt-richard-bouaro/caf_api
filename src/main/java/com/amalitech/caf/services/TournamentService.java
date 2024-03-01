package com.amalitech.caf.services;


import com.amalitech.caf.entities.HostEntity;
import com.amalitech.caf.entities.TournamentEntity;

import java.util.List;

public interface TournamentService {
    TournamentEntity createNewTournament(
            TournamentEntity tournamentEntity
    );

    List<TournamentEntity> getAllTournaments();

    TournamentEntity getTournament(Long id);

//    TournamentEntity updateTournament(Long id);

    boolean deleteTournament(Long id);
}
