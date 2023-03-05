package com.example.airbnbpractice.controller;

import com.example.airbnbpractice.common.dto.ResponseDto;
import com.example.airbnbpractice.common.security.UserDetailsImpl;
import com.example.airbnbpractice.dto.ReservationRequestDto;
import com.example.airbnbpractice.dto.ReservationResponseDto;
import com.example.airbnbpractice.service.ReservationService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Reservation")
@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping()
    public ResponseDto<ReservationResponseDto.ReservationRes> addReservation(
            @RequestBody ReservationRequestDto.ReservationAdd dto,
            @Parameter(hidden = true)  @AuthenticationPrincipal UserDetailsImpl userDetails
            ) {
        return ResponseDto.of(HttpStatus.OK, "등록 성공", reservationService.addReservation(dto, userDetails.getUser()));
    }

}
