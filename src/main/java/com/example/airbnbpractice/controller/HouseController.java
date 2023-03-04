package com.example.airbnbpractice.controller;

import com.example.airbnbpractice.common.dto.ResponseDto;
import com.example.airbnbpractice.common.security.UserDetailsImpl;
import com.example.airbnbpractice.dto.HouseRequestDto;
import com.example.airbnbpractice.dto.HouseResponseDto;
import com.example.airbnbpractice.service.HouseService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "House")
@RestController
@RequestMapping("/api/houses")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;


    @GetMapping()
    @SecurityRequirements()
    public ResponseDto<List<HouseResponseDto.HouseRes>> getHouses(
            @RequestParam(required = false) String adminDistrict,
            @RequestParam(required = false, defaultValue = "3") Integer peopleCount,
            @RequestParam(required = false, defaultValue = "0") Integer minPrice,
            @RequestParam(required = false, defaultValue = "100000") Integer maxPrice,
            @RequestParam(defaultValue = "2023-03-04") String startDate,
            @RequestParam(defaultValue = "2023-03-10") String endDate,
            @RequestParam(defaultValue = "0")  Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "false") Boolean isAsc,
            @RequestParam(required = false) Long userId
    ) {

        return ResponseDto.of(HttpStatus.OK, "등록 성공", houseService.getHouses(
                adminDistrict, peopleCount, minPrice, maxPrice,
                LocalDate.parse(startDate), LocalDate.parse(endDate), page, size, sortBy, isAsc, userId));
    }

    @PostMapping("/wish/{houseId}")
    public ResponseDto<Boolean> toggleWish(@PathVariable Long houseId,
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return houseService.toggleWish(houseId, userDetails.getUser());
    }


    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseDto<HouseResponseDto.HouseRes> addHouse(
            @RequestPart HouseRequestDto.HouseAdd dto,
            @RequestPart(required = false) MultipartFile thumbnailImage,
            @RequestPart(required = false,value = "houseImage") List<MultipartFile> houseImages,
            @Parameter(hidden = true)  @AuthenticationPrincipal UserDetailsImpl userDetails
            ) {

        HouseResponseDto.HouseRes res = houseService.addHouse(dto,thumbnailImage, houseImages, userDetails.getUser());
        return ResponseDto.of(HttpStatus.OK, "등록 성공", res);
    }

    @PutMapping(value = "/{houseId}")
    public ResponseDto<HouseResponseDto.HouseRes> updateHouse(
            @PathVariable Long houseId,
            @RequestBody HouseRequestDto.HouseUpdate dto,
            @Parameter(hidden = true)  @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        HouseResponseDto.HouseRes res = houseService.updateHouse(houseId, dto, userDetails.getUser());
        return ResponseDto.of(HttpStatus.OK, "수정 성공", res);
    }

    @GetMapping(value = "/registration")
    public ResponseDto<List<HouseResponseDto.HouseRes>> registration(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){
        List<HouseResponseDto.HouseRes> res = houseService.registration(userDetails);
        return ResponseDto.of(HttpStatus.OK, "수정 성공", res);
    }
}
