package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.*;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;

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

        private int likeCount;
        private List<HouseImageResponseDto> houseImages;

        private List<TagResponseDto> tags;

        private UserResponseDto owner;

        public static HouseRes of(House house) {
            return HouseRes.builder()
                    .id(house.getId())
                    .maxPeople(house.getMaxPeople())
                    .thumbnailUrl(house.getThumbnailUrl())
                    .adminDistrict(house.getAdminDistrict())
                    .detailAddress(house.getDetailAddress())
                    .content(house.getContent())
                    .likeCount(ObjectUtils.defaultIfNull(house.getLikeUsers(), new ArrayList<HouseWish>()).size())
                    .pricePerDay(house.getPricePerDay())
                    .houseImages(house.getHouseImages().stream().map(HouseImageResponseDto::of).toList())
                    .tags(house.getHouseTags().stream().map(v -> TagResponseDto.of(v.getTag())).toList())
                    .owner(UserResponseDto.of(house.getOwner()))
                    .build();
        }

        public static HouseRes noOwnerOf(House house) {
            return HouseRes.builder()
                    .id(house.getId())
                    .maxPeople(house.getMaxPeople())
                    .thumbnailUrl(house.getThumbnailUrl())
                    .adminDistrict(house.getAdminDistrict())
                    .detailAddress(house.getDetailAddress())
                    .content(house.getContent())
                    .likeCount(ObjectUtils.defaultIfNull(house.getLikeUsers(), new ArrayList<HouseWish>()).size())
                    .pricePerDay(house.getPricePerDay())
                    .houseImages(house.getHouseImages().stream().map(HouseImageResponseDto::of).toList())
                    .tags(house.getHouseTags().stream().map(v -> TagResponseDto.of(v.getTag())).toList())
                    .build();
        }
    }
}
