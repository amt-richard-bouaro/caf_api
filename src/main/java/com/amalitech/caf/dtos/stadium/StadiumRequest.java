package com.amalitech.caf.dtos.stadium;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class StadiumRequest {
    private Long id;

    @NotBlank(message = "host id is required")
    private Long host;

    @NotBlank(message = "City name is required")
    private String city;

    @NotBlank(message = "Image must be uploaded")
    private MultipartFile image;

    @NotBlank(message = "Name is required")
    private String name;

    @Digits(message = "capacity must be a number", integer = 10, fraction = 0)
    @Size(min = 1, max = 10, message = "capacity must be between 1 and 10 (inclusive)")
    private Long capacity;
}
