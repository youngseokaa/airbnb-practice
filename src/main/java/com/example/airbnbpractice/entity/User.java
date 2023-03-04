package com.example.airbnbpractice.entity;

import com.example.airbnbpractice.dto.SignupRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String profileImageURL;

    private String nickname;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Builder
    public User (String email, String nickname ,String password ,UserRoleEnum userRoleEnum,String profileImageURL,Long id){
        this.profileImageURL = profileImageURL;
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.role = userRoleEnum;
    }

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<House> houses;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Reservation> reservation;
}
