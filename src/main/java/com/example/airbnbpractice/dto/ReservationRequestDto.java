package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.House;
import com.example.airbnbpractice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class ReservationRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ReservationAdd {
        private LocalDate checkin;
        private LocalDate checkout;
        private Integer peopleCount;
        private Long houseId;
    }
}
