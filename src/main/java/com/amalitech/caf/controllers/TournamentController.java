package com.amalitech.caf.controllers;

import com.amalitech.caf.dtos.global.SuccessResponse;
import com.amalitech.caf.dtos.tournament.TournamentRequest;
import com.amalitech.caf.dtos.tournament.TournamentResponse;
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
    public ResponseEntity<SuccessResponse<TournamentResponse>> createTournaments(@Valid @RequestBody TournamentRequest payload) {

        TournamentEntity createdTournament = tournamentService.createNewTournament(payload);

        TournamentResponse res = tournamentMapper.mapFromEntityToDto(createdTournament);
        SuccessResponse<TournamentResponse> successResponse = new SuccessResponse<>(ResponseStatus.SUCCESS, "Tournament created successfully", Instant.now().toString(), res);
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }

    @Operation(description = "Get tournament history",

            summary = "This endpoint provide historical data on successfully CAF tournaments since its inception.",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403")})

    @GetMapping(path = "")
    public ResponseEntity<SuccessResponse<List<TournamentResponse>>> getTournaments() {
        List<TournamentEntity> tournaments = tournamentService.getAllTournaments();
        List<TournamentResponse> res = tournaments.stream().map(tournamentMapper::mapFromEntityToDto).toList();
        SuccessResponse<List<TournamentResponse>> successResponse = new SuccessResponse<>(ResponseStatus.SUCCESS, "Tournaments found", Instant.now().toString(), res);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SuccessResponse<TournamentResponse>> getTournaments(@PathVariable("id") Long id) {
        TournamentEntity tournament = tournamentService.getTournament(id);
        TournamentResponse foundTournament = tournamentMapper.mapFromEntityToDto(tournament);
        SuccessResponse<TournamentResponse> successResponse = new SuccessResponse<>(ResponseStatus.SUCCESS, "Tournament found", Instant.now().toString(), foundTournament);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SuccessResponse<NullType>> deleteTournaments(@PathVariable("id") Long id) {
        boolean deleted = this.tournamentService.deleteTournament(id);
        String deletionStatus = deleted ? "successfully" : "failed";
        SuccessResponse<NullType> res = new SuccessResponse<>(ResponseStatus.SUCCESS, "Tournament with id: " + id + " deletion " + deletionStatus, Instant.now().toString(), null);
        return new ResponseEntity<>(res, deleted ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }
}
