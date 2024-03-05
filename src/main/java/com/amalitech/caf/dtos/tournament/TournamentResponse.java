package com.amalitech.caf.dtos.tournament;

import com.amalitech.caf.dtos.host.HostResponse;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TournamentResponse {
    private Long id;
    private String name;
    private String edition;
    private List<HostResponse> hosts;
}
