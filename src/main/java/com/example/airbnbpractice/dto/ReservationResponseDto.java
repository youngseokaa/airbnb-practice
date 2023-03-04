package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.House;
import com.example.airbnbpractice.entity.Reservation;
import com.example.airbnbpractice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

public class ReservationResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ReservationRes {
        private Long id;
        private LocalDate checkin;
        private LocalDate checkout;
        private Integer peopleCount;
        private UserResponseDto user;
        private HouseResponseDto.HouseRes house;

        public static ReservationRes of(Reservation reservation) {
            return ReservationRes.builder()
                    .id(reservation.getId())
                    .checkin(reservation.getCheckin())
                    .checkout(reservation.getCheckout())
                    .peopleCount(reservation.getPeopleCount())
                    .user(UserResponseDto.of(reservation.getUser()))
                    .house(HouseResponseDto.HouseRes.of(reservation.getHouse()))
                    .build();
        }
    }

}
