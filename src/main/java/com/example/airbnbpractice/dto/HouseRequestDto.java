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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class HouseRequestDto {

    @Getter
    @AllArgsConstructor
    public static class HouseAdd {

        @NotNull
        @NotBlank
        @Schema(defaultValue = "서울특별시")
        private String adminDistrict;
        @NotNull
        @NotBlank
        @Schema(defaultValue = "상세주소")
        private String detailAddress;
        @NotNull
        @NotBlank
        @Schema(defaultValue = "숙소 설명")
        private String content;
        @NotNull
        @NotBlank
        @Schema(defaultValue = "10", minimum = "1", maximum = "20")
        private Integer maxPeople;
        @NotNull
        @NotBlank
        @Schema(defaultValue = "100000", minimum = "100", maximum = "10000000")
        private Integer pricePerDay;
        @Nullable
        @NotBlank
        @Schema(defaultValue = "[2,3]")
        private Set<Long> tagIds;

        @NotNull
        @NotBlank
        @Schema(description = "메인화면에서 보는 썸네일과, 상세페이지에서 보여주는 대표사진을 담당합니다")
        private MultipartFile thumbnailImage;
        @NotNull
        @NotBlank
        @Schema(description = "상세페이지에서 보여주는 세부사진 4장을 담당합니다")
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

        @Nullable
        @Schema(defaultValue = "서울특별시")
        private String adminDistrict;
        @Nullable
        @Schema(defaultValue = "상세주소")
        private String detailAddress;
        @Nullable
        @Schema(defaultValue = "숙소 정보")
        private String content;
        @Nullable
        @Schema(defaultValue = "10", minimum = "1", maximum = "20")
        private Integer maxPeople;
        @Nullable
        @Schema(defaultValue = "100000", minimum = "100", maximum = "10000000")
        private Integer pricePerDay;

        @Nullable
        @Schema(defaultValue = "[2,3]")
        private Set<Long> tagIds;

        @Nullable
        @NotBlank
        @Schema(description = "메인화면에서 보는 썸네일과, 상세페이지에서 보여주는 대표사진을 담당합니다")
        private MultipartFile thumbnailImage;

        @Nullable
        @NotBlank
        @Schema(description = "상세페이지에서 보여주는 세부사진 4장을 담당합니다")
        List<MultipartFile> houseImages;
    }
}
