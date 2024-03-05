package com.amalitech.caf.dtos.tournament;

import com.amalitech.caf.dtos.host.HostRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TournamentRequest {

    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotBlank(message = "Edition cannot be null")
    private String edition;

    @NotNull(message = "Provide Hosts")
    private List<HostRequest> hosts;

}


