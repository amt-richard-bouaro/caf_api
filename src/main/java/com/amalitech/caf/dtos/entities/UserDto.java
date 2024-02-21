package com.amalitech.caf.dtos.entities;

import com.amalitech.caf.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String password;
    
    private Role role;
}
