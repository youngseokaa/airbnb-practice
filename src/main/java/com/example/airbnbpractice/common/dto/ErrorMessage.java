package com.example.airbnbpractice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    NOT_NULL("필수값이 누락되었습니다"),
    PATTERN("잘못된 양식의 값이 있습니다.");


    private final String description;
}
