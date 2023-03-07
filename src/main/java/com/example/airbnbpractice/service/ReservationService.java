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

import java.time.temporal.ChronoUnit;
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

        if (dto.getPeopleCount() > house.getMaxPeople()) {
            throw CustomClientException.of(ErrorMessage.OVER_MAX_PEOPLE);
        }

        Integer diffDays = (int) ChronoUnit.DAYS.between(dto.getCheckin(), dto.getCheckout()) + 1;

        Integer payAmount = dto.getPeopleCount() * house.getPricePerDay() * diffDays;

        Reservation reservation = Reservation.builder()
                .user(user)
                .house(house)
                .checkin(dto.getCheckin())
                .checkout(dto.getCheckout())
                .peopleCount(dto.getPeopleCount())
                .payAmount(payAmount)
                .build();

        return ReservationResponseDto.ReservationRes.of(reservationRepository.save(reservation));
    }

    @Transactional
    public List<ReservationResponseDto.ReservationRes> reservedByUser(User user) {

        return reservationRepository.findByUser_Id(user.getId()).stream().map(ReservationResponseDto.ReservationRes::of).toList();
    }


}
