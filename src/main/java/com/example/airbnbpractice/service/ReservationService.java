package com.example.airbnbpractice.service;

import com.example.airbnbpractice.common.CustomClientException;
import com.example.airbnbpractice.common.dto.ErrorMessage;
import com.example.airbnbpractice.dto.ReservationRequestDto;
import com.example.airbnbpractice.dto.ReservationResponseDto;
import com.example.airbnbpractice.entity.House;
import com.example.airbnbpractice.entity.Reservation;
import com.example.airbnbpractice.entity.User;
import com.example.airbnbpractice.repository.HouseRepository;
import com.example.airbnbpractice.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final HouseRepository houseRepository;

    @Transactional
    public ReservationResponseDto.ReservationRes addReservation(ReservationRequestDto.ReservationAdd dto, User user) {
        House house = houseRepository.findById(dto.getHouseId()).orElseThrow(
                () -> CustomClientException.of(ErrorMessage.NO_HOUSE)
        );
        Reservation reservation = Reservation.builder()
                .user(user)
                .house(house)
                .checkin(dto.getCheckin())
                .checkout(dto.getCheckout())
                .peopleCount(dto.getPeopleCount())
                .build();

        return ReservationResponseDto.ReservationRes.of(reservationRepository.save(reservation));
    }
}
