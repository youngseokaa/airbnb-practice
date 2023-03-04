package com.example.airbnbpractice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    @Schema(defaultValue = "test@test.com")
    private String email;
    @Schema(defaultValue = "Test1234!")
    private String password;
}