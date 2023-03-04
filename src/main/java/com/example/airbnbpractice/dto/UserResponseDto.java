package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.User;
import com.example.airbnbpractice.entity.UserRoleEnum;

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
}
