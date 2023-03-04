package com.example.airbnbpractice.service;

import com.example.airbnbpractice.common.CustomClientException;
import com.example.airbnbpractice.common.dto.ErrorMessage;
import com.example.airbnbpractice.common.s3.S3Service;
import com.example.airbnbpractice.common.security.UserDetailsImpl;
import com.example.airbnbpractice.dto.HouseRequestDto;
import com.example.airbnbpractice.dto.HouseResponseDto;
import com.example.airbnbpractice.entity.*;
import com.example.airbnbpractice.repository.HouseRepository;
import com.example.airbnbpractice.repository.TagRepository;
import com.example.airbnbpractice.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;
    private final TagRepository tagRepository;
    private final S3Service s3Service;
    private final UserRepository userRepository;

    @Transactional
    public HouseResponseDto.HouseRes addHouse(
            HouseRequestDto.HouseAdd dto,
            MultipartFile thumbnailImage, List<MultipartFile> houseImages,
            User user) {

        List<Tag> tags = tagRepository.findByIdIn(dto.getTagIds());

        String thumbnailImageURL = s3Service.uploadSingle(thumbnailImage);
        List<String> houseImageURLs = s3Service.upload(houseImages);

        House house = House.builder().thumbnailUrl(thumbnailImageURL).dto(dto).ownerId(user.getId()).build();

        for (String houseImageURL : houseImageURLs) {
            house.addHouseImage(HouseImage.builder().house(house).imageURL(houseImageURL).build());
        }

        for (Tag tag : tags) {
            house.addHouseTag(HouseTag.builder().house(house).tag(tag).build());
        }

        house = houseRepository.save(house);

        return HouseResponseDto.HouseRes.noOwnerOf(house);
    }

    @Transactional
    public HouseResponseDto.HouseRes updateHouse(Long houseId, HouseRequestDto.HouseUpdate dto, User user) {

        House house = houseRepository.findById(houseId).orElseThrow(
                () -> CustomClientException.of(ErrorMessage.NO_HOUSE)
        );

        if (!house.getOwnerId().equals(user.getId())) {
            throw CustomClientException.of(ErrorMessage.EDIT_REJECT);
        }

        house.update(dto);

        return HouseResponseDto.HouseRes.noOwnerOf(house);
    }

    @Transactional
    public List<HouseResponseDto.HouseRes> registration(UserDetailsImpl userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> CustomClientException.of(ErrorMessage.NO_USER)
        );
        List<House> houses = houseRepository.findAllByOwnerId(user.getId());
        if(houses == null){
            throw CustomClientException.of(ErrorMessage.NO_HOUSE);
        }
        List<HouseResponseDto.HouseRes> dtoList = new ArrayList<>();
        for (House house : houses) {
            HouseResponseDto.HouseRes houseGet = HouseResponseDto.HouseRes.of(house);
            dtoList.add(houseGet);
        }
        return dtoList;
    }
    }

