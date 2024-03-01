package com.amalitech.caf.dtos.auth;

import com.amalitech.caf.enums.Role;
import com.amalitech.caf.validation.ComplexPassword;
import com.amalitech.caf.validation.PasswordsMatching;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PasswordsMatching(
        password = "password",
        confirmPassword = "confirmPassword",
        message = "Password and Confirm Password must be matched!"
)
public class NewUserPayload {

    @NotBlank(message = "Email must not be empty")
    private String firstName;

    @NotBlank(message = "Email must not be empty")
    private String lastName;

    @NotBlank(message = "Email must not be empty")
    @Email(message = "Invalid email address provided. Please enter a valid email address")

    private String email;
    @NotBlank(message = "Email must not be empty")

    @Size(min = 8, message = "Please provide password with length not less than 8 characters")
    private String password;

    @NotBlank(message = "Please confirm password")
    private String confirmPassword;
    @NotBlank(message = "User role must not be empty")
    private Role role;
}
