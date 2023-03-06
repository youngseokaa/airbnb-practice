package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.Tag;
import com.example.airbnbpractice.entity.TagType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class TagTypeReadDto {
    private Long id;
    private String name;
    private List<TagResponseDto> tags;

    public static TagTypeReadDto of(TagType tagType){
        return TagTypeReadDto.builder()
                .id(tagType.getId())
                .name(tagType.getName())
                .tags(tagType.getTags().stream().map(TagResponseDto::of).toList())
                .build();
    }
}
