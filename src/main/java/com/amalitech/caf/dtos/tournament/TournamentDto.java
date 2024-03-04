package com.amalitech.caf.dtos.tournament;

import com.amalitech.caf.dtos.host.HostDto;
import com.amalitech.caf.dtos.host.NewHostPayloadDto;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TournamentDto {
    private Long id;
    private String name;
    private String edition;
    private List<HostDto> hosts;
}
