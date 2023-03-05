package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class HouseRequestDto {

    @Getter
    @AllArgsConstructor
    public static class HouseAdd {

        @NotNull
        @Schema(defaultValue = "서울특별시")
        private String adminDistrict;
        @NotNull
        @Schema(defaultValue = "상세주소")
        private String detailAddress;
        @NotNull
        @Schema(defaultValue = "숙소 정보")
        private String content;
        @NotNull
        @Schema(defaultValue = "10", minimum = "1", maximum = "20")
        private Integer maxPeople;
        @NotNull
        @Schema(defaultValue = "100000", minimum = "100", maximum = "10000000")
        private Integer pricePerDay;
        @Nullable
        @Schema(defaultValue = "[2,3]")
        private Set<Long> tagIds;

        @NotNull
        private MultipartFile thumbnailImage;
        @NotNull
        List<MultipartFile> houseImages;
    }

    @Getter
    @AllArgsConstructor
    public static class SearchHouse {
        private String adminDistrict;
        private String detailAddress;
        private String content;
        private Integer peopleCount;
        private Integer minPrice;
        private Integer maxPrice;

        private LocalDate startDate;
        private LocalDate endDate;

        private Integer page;
        private Integer size;
        private String sortBy;

        private Boolean isAsc;

        private Long userId;
    }


    @Getter
    @AllArgsConstructor
    public static class HouseUpdate {

        private String adminDistrict;
        private String detailAddress;

        private String content;

        private Integer maxPeople;

        private Integer pricePerDay;
    }
}
