package com.amalitech.caf.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewHostPayloadDto {
    @NotEmpty
    private String country;
    
    @NotEmpty
    private List<String> cities;
}
