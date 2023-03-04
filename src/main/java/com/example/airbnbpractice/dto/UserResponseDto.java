package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.User;
import com.example.airbnbpractice.entity.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;

    private String nickname;

    private String email;

    private UserRoleEnum role;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public static UserResponseDto of(User user) {
        return UserResponseDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .id(user.getId())
                .role(user.getRole())
                .build();
    }
}
