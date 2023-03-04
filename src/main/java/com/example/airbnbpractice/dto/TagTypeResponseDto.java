package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.TagType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TagTypeResponseDto {
    private Long id;
    private String name;

    public static TagTypeResponseDto of(TagType tagType) {
        return TagTypeResponseDto.builder()
                .id(tagType.getId())
                .name(tagType.getName())
                .build();
    }
}
