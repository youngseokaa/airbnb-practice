package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckResponseDto {
    private Boolean duplicate;


    public CheckResponseDto(){
        duplicate = true;
    }
}
