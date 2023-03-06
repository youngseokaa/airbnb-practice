package com.example.airbnbpractice.dto;

import com.example.airbnbpractice.entity.House;
import com.example.airbnbpractice.entity.TagType;
import com.example.airbnbpractice.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Getter
@Setter
public class TagRequestDto {
    private String name;
    private MultipartFile imageFile;
}
