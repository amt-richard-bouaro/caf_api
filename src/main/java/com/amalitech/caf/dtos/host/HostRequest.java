package com.amalitech.caf.dtos.host;

import com.amalitech.caf.dtos.stadium.StadiumResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostRequest {
    @NotBlank
    private String country;

    private List<StadiumResponse> cities = new ArrayList<>();
}
