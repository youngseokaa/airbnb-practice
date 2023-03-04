package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.Tag;
import com.example.airbnbpractice.entity.TagType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class TagResponseDto {

    private Long id;
    private String name;
    private String imageURL;

    private TagTypeResponseDto tagType;

    public static TagResponseDto of(Tag tag) {
        return TagResponseDto.builder()
                .imageURL(tag.getImageURL())
                .name(tag.getName())
                .id(tag.getId())
                .tagType(TagTypeResponseDto.of(tag.getTagType()))
                .build();
    }
}
