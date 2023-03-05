package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.*;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

public class HouseResponseDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class HouseRes {
        private Long id;

        private String thumbnailUrl;
        private String adminDistrict;
        private String detailAddress;
        private String content;
        private Integer maxPeople;
        private Integer pricePerDay;

        private Boolean isLike;

        private int likeCount;
        private List<HouseImageResponseDto> houseImages;

        private List<TagResponseDto> tags;

        private UserResponseDto owner;

        public static HouseRes of(House house, Long userId) {

            List<HouseWish> wishHouses = ObjectUtils.defaultIfNull(house.getWishHouses(), new ArrayList<HouseWish>()).stream().toList();
            HouseResBuilder builder = HouseRes.builder()
                    .id(house.getId())
                    .maxPeople(house.getMaxPeople())
                    .thumbnailUrl(house.getThumbnailUrl())
                    .adminDistrict(house.getAdminDistrict())
                    .detailAddress(house.getDetailAddress())
                    .content(house.getContent())
                    .likeCount(wishHouses.size())
                    .pricePerDay(house.getPricePerDay())
                    .houseImages(house.getHouseImages().stream().map(HouseImageResponseDto::of).toList())
                    .tags(house.getHouseTags().stream().map(v -> TagResponseDto.of(v.getTag())).toList())
                    .owner(UserResponseDto.of(house.getOwner()));


            builder.isLike(wishHouses.stream().anyMatch(v -> v.getUserId().equals(userId)));

            return builder.build();
        }

        public static HouseRes noOwnerOf(House house, Long userId) {

             List<HouseWish> wishHouses = ObjectUtils.defaultIfNull(house.getWishHouses(), new ArrayList<HouseWish>()).stream().toList();
            HouseResBuilder builder = HouseRes.builder()
                    .id(house.getId())
                    .maxPeople(house.getMaxPeople())
                    .thumbnailUrl(house.getThumbnailUrl())
                    .adminDistrict(house.getAdminDistrict())
                    .detailAddress(house.getDetailAddress())
                    .content(house.getContent())
                    .likeCount(wishHouses.size())
                    .pricePerDay(house.getPricePerDay())
                    .houseImages(house.getHouseImages().stream().map(HouseImageResponseDto::of).toList())
                    .tags(house.getHouseTags().stream().map(v -> TagResponseDto.of(v.getTag())).toList());

            builder.isLike(wishHouses.stream().anyMatch(v -> v.getUserId().equals(userId)));

            return builder.build();
        }
    }
}
