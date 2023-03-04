package com.example.airbnbpractice.controller;

import com.example.airbnbpractice.common.dto.ResponseDto;
import com.example.airbnbpractice.common.security.UserDetailsImpl;
import com.example.airbnbpractice.dto.HouseRequestDto;
import com.example.airbnbpractice.dto.HouseResponseDto;
import com.example.airbnbpractice.service.HouseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "House")
@RestController
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    @PostMapping(value = "/api/house", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseDto<HouseResponseDto.HouseRes> addHouse(
            @RequestPart HouseRequestDto.HouseAdd dto,
            @RequestPart(required = false)MultipartFile thumbnailImage,
            @RequestPart(required = false,value = "houseImage") List<MultipartFile> houseImages,
            @AuthenticationPrincipal UserDetailsImpl userDetails
            ) {
        ;

        HouseResponseDto.HouseRes res = houseService.addHouse(dto,thumbnailImage, houseImages, userDetails.getUser());
        return ResponseDto.of(HttpStatus.OK, "등록 성공", res);
    }
}
