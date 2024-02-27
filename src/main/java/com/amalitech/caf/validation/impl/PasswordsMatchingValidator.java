package com.amalitech.caf.validation.impl;

import com.amalitech.caf.validation.PasswordsMatching;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Objects;

public class PasswordsMatchingValidator implements ConstraintValidator<PasswordsMatching, Object> {
    private String password;
    private String confirmPassword;

    @Override
    public void initialize(PasswordsMatching matching) {
        this.password = matching.password();
        this.confirmPassword = matching.confirmPassword();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(password);
        Object confirmPasswordValue = new BeanWrapperImpl(value).getPropertyValue(confirmPassword);

        return Objects.equals(passwordValue, confirmPasswordValue);
    }
}
