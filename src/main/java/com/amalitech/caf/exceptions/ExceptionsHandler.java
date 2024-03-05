package com.amalitech.caf.exceptions;

import com.amalitech.caf.dtos.global.ErrorResponseDto;
import com.amalitech.caf.enums.ResponseStatus;
import jakarta.servlet.UnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.lang.model.type.NullType;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseDto> handlerConflictException(
            ConflictException exception,
            WebRequest request
    ) {
        ErrorResponseDto responseDTO = new ErrorResponseDto<NullType>(ResponseStatus.ERROR, exception.getMessage(),
                Instant.now()
                        .toString(), null);
        return new ResponseEntity<>(responseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlerNotFoundException(
            NotFoundException exception,
            WebRequest request
    ) {
        ErrorResponseDto responseDTO = new ErrorResponseDto<NullType>(ResponseStatus.ERROR, exception.getMessage(),
                Instant.now()
                        .toString(), null);
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseDto> handlerUnauthorizedException(
            UnauthorizedException exception,
            WebRequest request
    ) {

        ErrorResponseDto responseDTO = new ErrorResponseDto<NullType>(ResponseStatus.ERROR, exception.getMessage(),
                Instant.now()
                        .toString(), null);
        return new ResponseEntity<>(responseDTO, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(UnavailableException.class)
    public ResponseEntity<ErrorResponseDto> handlerUnavailableException(
            UnavailableException exception,
            WebRequest request
    ) {

        ErrorResponseDto responseDTO = new ErrorResponseDto<NullType>(ResponseStatus.ERROR, exception.getMessage(),
                Instant.now()
                        .toString(), null);
        return new ResponseEntity<>(responseDTO, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDto<NullType>> handlerUsernameNotFoundException(
            UsernameNotFoundException exception,
            WebRequest request
    ) {

        ErrorResponseDto<NullType> responseDTO = new ErrorResponseDto<>(ResponseStatus.ERROR, exception.getMessage(),
                Instant.now()
                        .toString(), null);
        return new ResponseEntity<>(responseDTO, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto<Map<String, String>>> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exception,
            WebRequest request
    ) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    if (error instanceof FieldError fieldError) {
                        errors.put("field", fieldError.getField());
                        errors.put("cause", fieldError.getDefaultMessage());
                    } else {
                        errors.put(error.getObjectName(), error.getDefaultMessage());
                    }
                });

        ErrorResponseDto<Map<String, String>> responseDTO = new ErrorResponseDto<>(ResponseStatus.ERROR,
                "Validation Error: Payload not well formed", Instant.now()
                .toString(), errors);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

}
