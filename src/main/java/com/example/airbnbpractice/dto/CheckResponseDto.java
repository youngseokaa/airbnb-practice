package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CheckResponseDto {
    private Boolean duplicate;


    public CheckResponseDto(){
        duplicate = true;
    }
}
