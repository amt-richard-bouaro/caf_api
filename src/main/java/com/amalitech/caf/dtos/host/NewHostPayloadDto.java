package com.amalitech.caf.dtos.host;

import com.amalitech.caf.entities.StadiumEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class NewHostPayloadDto {
    @NotBlank
    private String country;

    private List<StadiumEntity> cities = new ArrayList<>();
}
