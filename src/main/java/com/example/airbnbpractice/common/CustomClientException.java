package com.example.airbnbpractice.common;

import com.example.airbnbpractice.common.dto.ErrorMessage;

public class CustomClientException extends IllegalArgumentException {

    ErrorMessage errorMessage;

    public CustomClientException(ErrorMessage errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public static CustomClientException of(ErrorMessage errorMessage) {
        return new CustomClientException(errorMessage);
    }
}
