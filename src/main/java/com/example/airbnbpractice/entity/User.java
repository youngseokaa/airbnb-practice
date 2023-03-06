package com.example.airbnbpractice.entity;

import com.example.airbnbpractice.dto.SignupRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;


@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(example = "test@test.com", description = "로그인에 사용할 이메일. 중복되면 안됩니다.")
    private String email;

    @Schema(description = "사용자를 구분할 프로필사진 입니다.")
    private String profileImageURL;

    @Schema(example = "운전피곤해", description = "사용자를 구분할 닉네임. 중복되면 안됩니다.")
    private String nickname;
    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
            message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @Schema(example = "", description = "")
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
