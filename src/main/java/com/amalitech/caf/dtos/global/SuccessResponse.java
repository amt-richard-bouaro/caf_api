package com.amalitech.caf.dtos.global;

import com.amalitech.caf.enums.ResponseStatus;

public record SuccessResponse<T>(ResponseStatus status, String message, String timestamp, T data) {
}
