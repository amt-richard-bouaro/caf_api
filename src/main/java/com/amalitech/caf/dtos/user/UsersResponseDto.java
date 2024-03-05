package com.amalitech.caf.dtos.user;

import com.amalitech.caf.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UsersResponseDto {
    private Long id;
    private String firstName;

    private String lastName;

    private String email;

    private Role role;
}
