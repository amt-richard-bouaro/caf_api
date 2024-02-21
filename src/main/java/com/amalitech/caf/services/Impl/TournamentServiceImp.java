package com.amalitech.caf.services.Impl;

import com.amalitech.caf.entities.TournamentEntity;
import com.amalitech.caf.repositories.TournamentRepository;
import com.amalitech.caf.services.TournamentService;
import org.springframework.stereotype.Service;

@Service
public class TournamentServiceImp implements TournamentService {
    
    private final TournamentRepository tournamentRepository;
    
    public TournamentServiceImp(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }
    
    @Override
    public TournamentEntity createNewTournament(
            TournamentEntity tournament
    
    ) {
        
        return tournamentRepository.save(tournament);
        
    }
}
