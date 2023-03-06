package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CheckResponseDto {
    @Schema(example = "중복여부", description = "중복이면 true로 반환, 중복이 아니면 false로 반환")
    private Boolean duplicate;

    public CheckResponseDto(){
        duplicate = true;
    }
}
