package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.HouseImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class HouseImageResponseDto {
    private Long id;
    private String imageURL;

    public static HouseImageResponseDto of (HouseImage houseImage) {
        return HouseImageResponseDto.builder()
                .imageURL(houseImage.getImageURL())
                .id(houseImage.getId())
                .build();
    }
}
