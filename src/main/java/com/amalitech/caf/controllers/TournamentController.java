package com.amalitech.caf.controllers;

import com.amalitech.caf.dtos.entities.TournamentDto;
import com.amalitech.caf.dtos.requests.NewTournamentDto;
import com.amalitech.caf.entities.HostEntity;
import com.amalitech.caf.entities.TournamentEntity;
import com.amalitech.caf.mappers.TournamentMapper;
import com.amalitech.caf.services.TournamentService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tournaments")
@RequiredArgsConstructor
@Tag(name = "Tournaments")
public class TournamentController {
    
    private final TournamentMapper tournamentMapper;
    
    private final TournamentService tournamentService;
    
    
    @PostMapping(path = "/create")
    public ResponseEntity<TournamentDto> createTournaments(@Valid @RequestBody NewTournamentDto payload) {
        
        TournamentEntity tournament = TournamentEntity.builder()
                                                      .name(payload.getName())
                                                      .edition(payload.getEdition())
                                                      .build();
        
        
        List<HostEntity> hosts = payload.getHosts()
                                        .stream()
                                        .map(host -> HostEntity.builder()
                                                               .country(host.getCountry())
                                                               .cities(host.getCities())
                                                               .tournament(tournament)
                                                               .build())
                                        .toList();
        
        tournament.setHosts(hosts);
        
        TournamentEntity createdTournament = tournamentService.createNewTournament(tournament);
        
        TournamentDto res = tournamentMapper.mapFromEntityToDto(createdTournament);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    
    @Operation(description = "Get tournament history", summary = "This endpoint provide historical data on successfully CAF tournaments since its inception.", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized", responseCode = "403")})
    @GetMapping(path = "/")
    public List<TournamentEntity> getTournaments() {
        return new ArrayList<>();
    }
    
    @GetMapping(path = "/{id}")
    public TournamentEntity getTournaments(@PathVariable("id") Long id) {
        return new TournamentEntity();
    }
    
    @Hidden
    @DeleteMapping(path = "/{id}")
    public TournamentEntity deleteTournaments(@PathVariable("id") Long id) {
        return new TournamentEntity();
    }
}
