package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.House;
import com.example.airbnbpractice.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
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

        @Schema(example = "Thu Mar 02 12:07:04 KST 2023", description = "시간까지 표기해줍니다. 요일, 월, 일, 시간, 기준시, 년도 순입니다")
        private LocalDate checkin;
        @Schema(example = "Thu Mar 02 14:07:04 KST 2023", description = "시간까지 표기해줍니다. 요일, 월, 일, 시간, 기준시, 년도 순입니다")
        private LocalDate checkout;
        @Schema(example = "3", description = "숙박할 인원수를 입력합니다. 숙소의 최대수용인원수 보다 같거나 작아야합니다.")
        private Integer peopleCount;
        @Schema(description = "예약을 희망하는 숙소의 아이디입니다")
        private Long houseId;
    }
}
