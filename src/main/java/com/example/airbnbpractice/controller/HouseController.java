package com.example.airbnbpractice.controller;

import com.example.airbnbpractice.common.dto.ResponseDto;
import com.example.airbnbpractice.common.security.UserDetailsImpl;
import com.example.airbnbpractice.dto.HouseRequestDto;
import com.example.airbnbpractice.dto.HouseResponseDto;
import com.example.airbnbpractice.service.HouseService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "숙소 검색", description = "최초 조회도 포함. 소속행정구역(특별시, 광역시는 도에서 분리)," +
            "희망 가격대 포함여부, 희망 기간 예약가능 여부를 검토하여 페이징처리하여 반환합니다.")
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
    @Operation(summary = "좋아요(찜, 위시) 추가/제거", description = "해당 숙소에 대한 사용자의 좋아요 선택 여부를 변경합니다." +
            "좋아요 표시된 숙소는 해당 사용자의 마이위시리스트에 추가됩니다.")
    public ResponseDto<Boolean> toggleWish(@PathVariable Long houseId,
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        if (houseService.toggleWish(houseId, userDetails.getUser())) {
            return ResponseDto.of(HttpStatus.OK,"위시리스트에 추가했습니다", true);
        }
        else {
            return ResponseDto.of(HttpStatus.OK,"위시리스트에서 제거했습니다", false);
        }
    }


    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "숙소 등록", description = "숙소를 추가합니다." +
            "행정구역, 상세주소, 숙소 정보(설명), 수용인원 수, 숙소 1박 가격, 편의시설, 썸네일사진과 세부사진 정보와 숙소등록유저정보를 저장합니다.")
    public ResponseDto<HouseResponseDto.HouseRes> addHouse(
            @ModelAttribute HouseRequestDto.HouseAdd dto,
            @Parameter(hidden = true)  @AuthenticationPrincipal UserDetailsImpl userDetails
            ) {

        HouseResponseDto.HouseRes res = houseService.addHouse(dto, userDetails.getUser());
        return ResponseDto.of(HttpStatus.OK, "등록 성공", res);
    }

    @PutMapping(value = "/{houseId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "숙소 수정", description = "기존에 등록한 숙소의 정보를 수정합니다")
    public ResponseDto<HouseResponseDto.HouseRes> updateHouse(
            @PathVariable Long houseId,
            @ModelAttribute HouseRequestDto.HouseUpdate dto,
            @Parameter(hidden = true)  @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        HouseResponseDto.HouseRes res = houseService.updateHouse(houseId, dto, userDetails.getUser());
        return ResponseDto.of(HttpStatus.OK, "수정 성공", res);
    }


    @GetMapping("/wish")
    @Operation(summary = "마이위시리스트 조회", description = "현재 이용중인 사용자가 좋아요(찜, 위시) 체크해 둔 숙소들을 보여줍니다")
    public ResponseDto<List<HouseResponseDto.HouseRes>> wishHouses(
            @Parameter(hidden = true)  @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseDto.of(HttpStatus.OK, "조회 완료", houseService.wishHouses(userDetails.getUser()));
    }


    @GetMapping("/{houseId}")
    @SecurityRequirements()
    @Operation(summary = "숙소 상세 조회", description = "숙소 하나의 정보를 상세조회합니다")
    public ResponseDto<HouseResponseDto.HouseRes> getHouse(@PathVariable Long houseId, @RequestParam(required = false) Long userId) {

        return ResponseDto.of(HttpStatus.OK, "조회 완료", houseService.getHouse(houseId, userId));
    }

    @GetMapping(value = "/registration")
    @Operation(summary = "내가 등록한 숙소 조회", description = "사용자가 직접 관리하고 등록한 숙소들을 보여줍니다")
    public ResponseDto<List<HouseResponseDto.HouseRes>> registration(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<HouseResponseDto.HouseRes> res = houseService.registration(userDetails);
        return ResponseDto.of(HttpStatus.OK, "수정 성공", res);
    }

    @DeleteMapping(value = "/{houseId}")
    public ResponseDto registration(
            @PathVariable Long houseId,
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){
        houseService.removeHouse(houseId, userDetails.getUser());
        return ResponseDto.of(HttpStatus.OK, "삭제 성공");
    }


}
