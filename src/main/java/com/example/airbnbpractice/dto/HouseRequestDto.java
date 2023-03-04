package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

public class HouseRequestDto {

    @Getter
    @AllArgsConstructor
    public static class HouseAdd {
        private String adminDistrict;
        private String detailAddress;
        private String content;
        private Integer maxPeople;
        private Integer pricePerDay;
        private Set<Long> tagIds;
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
