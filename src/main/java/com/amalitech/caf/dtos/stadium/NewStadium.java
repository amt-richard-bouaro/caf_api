package com.amalitech.caf.dtos.stadium;

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
public class NewStadium {
    private Long id;

    @NotBlank(message = "City name is required")
    private String city;
    @NotBlank(message = "Image must be uploaded")
    private MultipartFile image;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "capacity is required")
    @Size(min = 40000)
    private Long capacity;
}
