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
    EDIT_REJECT("수정할 권한이 없습니다."),

    NO_USER("가입된 유저가 없습니다."),
    NO_HOUSE("등록된 숙소가 없습니다."),
    NOT_MATCH_PASSWORD("비밀번호가 일치하지 않습니다."),
    TOKEN_EXPIRED("토큰이 만료되었습니다."),
    EXIST_NICKNAME("이미존재하는 닉네임 입니다"),
    EXIST_EMAIL("이미존재하는 이메일입니다");

    private final String description;
}
