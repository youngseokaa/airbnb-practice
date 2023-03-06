package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.House;
import com.example.airbnbpractice.entity.TagType;
import com.example.airbnbpractice.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class TagRequestDto {
    private String name;
    private String imageURL;
    private TagType tagType;
}
