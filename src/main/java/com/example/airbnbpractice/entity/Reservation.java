package com.example.airbnbpractice.entity;


import com.example.airbnbpractice.dto.ReservationRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "reservations")
@Getter
@NoArgsConstructor
public class Reservation extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(example = "2022-01-01", description = "년 월 일 순입니다")
    private LocalDate checkin;
    @Schema(example = "2022-01-01", description = "년 월 일 순입니다")
    private LocalDate checkout;
    @Schema(example = "3", description = "숙박할 인원수를 입력합니다. 숙소의 최대수용인원수 보다 같거나 작아야합니다.")
    private Integer peopleCount;
    private Boolean isCanceled;
    private Integer payAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private House house;

    @Builder
    public Reservation(Long id, LocalDate checkin, LocalDate checkout, Integer payAmount, Boolean isCanceled, Integer peopleCount, User user, House house) {
        this.id = id;
        this.checkin = checkin;
        this.checkout = checkout;
        this.peopleCount = peopleCount;
        this.user = user;
        this.payAmount = payAmount;
        this.isCanceled = false;
        this.house = house;
    }
}
