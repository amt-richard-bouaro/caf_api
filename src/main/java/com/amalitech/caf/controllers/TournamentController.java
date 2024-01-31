package com.amalitech.caf.controllers;

import com.amalitech.caf.models.entities.TournamentEntity;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
//@SecurityRequirement(name="BearerAuth")
@Tag(name="Tournaments")
public class TournamentController {

    @Operation(
            description = "Get tournament history",
            summary = "This endpoint provide historical data on successfully CAF tournaments since its inception.",
            responses = {
                @ApiResponse(
                        description = "Success",
                        responseCode = "200"
                ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping(path = "/v1")
    public List<TournamentEntity> getTournaments(){
        return new ArrayList<>();
    }
    @GetMapping(path = "/v1/{id}")
    public TournamentEntity getTournaments(@PathVariable("id") Long id){
        return new TournamentEntity();
    }

    @Hidden
    @DeleteMapping(path = "/v1/{id}")
    public TournamentEntity deleteTournaments(@PathVariable("id") Long id){
        return new TournamentEntity();
    }
}
