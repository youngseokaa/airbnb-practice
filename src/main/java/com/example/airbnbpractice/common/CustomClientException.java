package com.example.airbnbpractice.common;

import com.example.airbnbpractice.common.dto.ErrorMessage;

public class CustomClientException extends IllegalArgumentException {
    public CustomClientException(String s) {
        super(s);
    }

}
