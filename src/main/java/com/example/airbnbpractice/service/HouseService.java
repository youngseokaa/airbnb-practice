package com.example.airbnbpractice.service;

import com.example.airbnbpractice.common.s3.S3Service;
import com.example.airbnbpractice.dto.HouseRequestDto;
import com.example.airbnbpractice.dto.HouseResponseDto;
import com.example.airbnbpractice.entity.*;
import com.example.airbnbpractice.repository.HouseRepository;
import com.example.airbnbpractice.repository.TagRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;
    private final TagRepository tagRepository;
    private final S3Service s3Service;

    @Transactional
    public HouseResponseDto.HouseRes addHouse(
            HouseRequestDto.HouseAdd dto,
            MultipartFile thumbnailImage, List<MultipartFile> houseImages,
            User user) {

        List<Tag> tags = tagRepository.findByIdIn(dto.getTagIds());

        String thumbnailImageURL = s3Service.uploadSingle(thumbnailImage);
        List<String> houseImageURLs = s3Service.upload(houseImages);

        House house = House.builder().thumbnailUrl(thumbnailImageURL).dto(dto).owner(user).build();

        for (String houseImageURL : houseImageURLs) {
            house.addHouseImage(HouseImage.builder().house(house).imageURL(houseImageURL).build());
        }

        for (Tag tag : tags) {
            house.addHouseTag(HouseTag.builder().house(house).tag(tag).build());
        }

        house = houseRepository.save(house);

        return HouseResponseDto.HouseRes.of(house);
    }
}
