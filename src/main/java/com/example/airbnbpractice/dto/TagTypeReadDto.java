package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.Tag;
import com.example.airbnbpractice.entity.TagType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
public class TagTypeReadDto {
    private Long id;
    private String name;
    private String imageURL;
    private List<TagResponseDto> tags;

    public TagTypeReadDto(TagType tagType){
        this.id = tagType.getId();
        this.name = tagType.getName();
        List<TagResponseDto> tagResponseDtos = new ArrayList<>() {
        };
        for (Tag tags : tagType.getTags()) {
            tagResponseDtos.add(TagResponseDto.of(tags));
        }
        this.tags = tagResponseDtos;
    }
}
