package com.amalitech.caf.dtos.entities;

import com.amalitech.caf.entities.HostEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TournamentDto {
    private Long id;
    private String name;
    private String edition;
    private List<HostDto> hosts;
}
