package com.example.airbnbpractice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    NOT_NULL("필수값이 누락되었습니다"),
    PATTERN("잘못된 양식의 값이 있습니다."),
    TOKEN_ERROR("토큰이 유효하지 않습니다."),
    S3_UPLOAD_ERROR("이미지 업로드 실패"),
    DUPLE_USER("이미 가입된 유저가 있습니다."),
    TOKEN_EXPIRED("토큰이 만료되었습니다.");


    private final String description;
}
