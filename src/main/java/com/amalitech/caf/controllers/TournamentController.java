package com.amalitech.caf.controllers;

import com.amalitech.caf.dtos.global.SuccessResponse;
import com.amalitech.caf.dtos.stadium.StadiumDto;
import com.amalitech.caf.dtos.tournament.NewTournamentDto;
import com.amalitech.caf.dtos.tournament.TournamentDto;
import com.amalitech.caf.entities.HostEntity;
import com.amalitech.caf.entities.TournamentEntity;
import com.amalitech.caf.enums.ResponseStatus;
import com.amalitech.caf.mappers.TournamentMapper;
import com.amalitech.caf.services.TournamentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.type.NullType;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tournaments")
@RequiredArgsConstructor
@Tag(name = "Tournaments")
public class TournamentController {

    private final TournamentMapper tournamentMapper;

    private final TournamentService tournamentService;


    @PostMapping(path = "/create")
    public ResponseEntity<SuccessResponse<TournamentDto>> createTournaments(@Valid @RequestBody NewTournamentDto payload) {

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
        TournamentEntity createdTournament = tournamentService.createNewTournament(tournament);

        TournamentDto res = tournamentMapper.mapFromEntityToDto(createdTournament);
        SuccessResponse<TournamentDto> successResponse = new SuccessResponse<>(ResponseStatus.SUCCESS, "Tournament created successfully", Instant.now().toString(), res);
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }

    @Operation(description = "Get tournament history",

            summary = "This endpoint provide historical data on successfully CAF tournaments since its inception.",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403")})


    @GetMapping(path = "")
    public ResponseEntity<List<TournamentDto>> getTournaments() {
        List<TournamentEntity> tournaments = tournamentService.getAllTournaments();
        List<TournamentDto> res = tournaments.stream().map(tournamentMapper::mapFromEntityToDto).toList();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TournamentDto> getTournaments(@PathVariable("id") Long id) {
        TournamentEntity tournament = tournamentService.getTournament(id);
        TournamentDto foundTournament = tournamentMapper.mapFromEntityToDto(tournament);
        return new ResponseEntity<>(foundTournament, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SuccessResponse<NullType>> deleteTournaments(@PathVariable("id") Long id) {
        boolean deleted = this.tournamentService.deleteTournament(id);
        String deletionStatus = deleted ? "successfully" : "failed";
        SuccessResponse<NullType> res = new SuccessResponse<>(ResponseStatus.SUCCESS, "Tournament with id: " + id + " deletion " + deletionStatus, Instant.now().toString(), null);
        return new ResponseEntity<>(res, deleted ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }
}
