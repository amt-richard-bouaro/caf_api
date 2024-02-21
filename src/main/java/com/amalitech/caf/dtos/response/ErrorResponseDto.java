package com.amalitech.caf.dtos.response;

import com.amalitech.caf.enums.ResponseStatus;

import java.util.Date;

public record ErrorResponseDto<T>(ResponseStatus status, String message, String timestamp, T error) {
}
