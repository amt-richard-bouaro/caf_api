package com.amalitech.caf.services.Impl;

import com.amalitech.caf.dtos.tournament.TournamentRequest;
import com.amalitech.caf.entities.HostEntity;
import com.amalitech.caf.entities.TournamentEntity;
import com.amalitech.caf.exceptions.NotFoundException;
import com.amalitech.caf.repositories.TournamentRepository;
import com.amalitech.caf.services.TournamentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TournamentServiceImp implements TournamentService {

    private final TournamentRepository tournamentRepository;

    public TournamentServiceImp(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public TournamentEntity createNewTournament(
            TournamentRequest payload

    ) {
        TournamentEntity tournament = TournamentEntity.builder()
                .name(payload.getName())
                .edition(payload.getEdition())
                .build();

        List<HostEntity> hosts = payload.getHosts()
                .stream()
                .map(host -> HostEntity.builder()
                        .country(host.getCountry())
                        .tournament(tournament)
                        .build())
                .toList();

        tournament.setHosts(hosts);
        return tournamentRepository.save(tournament);

    }

    @Override
    public TournamentEntity getTournament(Long id) {

        TournamentEntity foundTournament = tournamentRepository.findById(id).orElse(null);

        if (foundTournament == null) {
            throw new NotFoundException("Tournament with id " + id + " not found");
        }
        return foundTournament;


    }


    @Override
    public List<TournamentEntity> getAllTournaments() {

        return tournamentRepository.findAll();
    }


    @Override
    public boolean deleteTournament(Long id) {
        if (!tournamentRepository.existsById(id)) {
            throw new NotFoundException("Tournament with id " + id + " not found");
        }

        tournamentRepository.deleteById(id);
        return true;
    }
}
