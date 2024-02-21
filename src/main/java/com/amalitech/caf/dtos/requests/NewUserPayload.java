package com.amalitech.caf.dtos.requests;

import com.amalitech.caf.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewUserPayload {
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String password;
    private Role role;
}
