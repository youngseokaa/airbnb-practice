package com.example.airbnbpractice.controller;

import com.example.airbnbpractice.common.dto.ResponseDto;

import com.example.airbnbpractice.dto.*;
import com.example.airbnbpractice.entity.UserRoleEnum;
import com.example.airbnbpractice.service.TagTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TagType")
@RestController
@RequestMapping("/api/tagTypes")
@RequiredArgsConstructor
public class TagTypeController {

    private final TagTypeService tagTypeService;

    @PostMapping
    @Secured(UserRoleEnum.Authority.ADMIN)
    @Operation(summary = "태그 타입 등록", description = "태그를 분류하는 태그타입을 추가합니다.")
    public ResponseDto<TagTypeResponseDto> addTagType(@RequestBody TagTypeRequestDto tagTypeRequestDto){
        TagTypeResponseDto trd = tagTypeService.addTagType(tagTypeRequestDto);
        return ResponseDto.of(HttpStatus.OK,"태그 타입 등록에 성공하였습니다",trd);
    }

    @GetMapping
    @SecurityRequirements()
    @Operation(summary = "태그 타입 조회", description = "현재까지 등록된 모든 태그타입을 조회합니다.")
    public ResponseDto<List<TagTypeReadDto>> readTagType(){
        List<TagTypeReadDto> trd = tagTypeService.readTagType();
        return ResponseDto.of(HttpStatus.OK,"태그 타입을 읽어 오는것에 성공 하였습니다",trd);
    }

    @GetMapping("/byHome")
    @SecurityRequirements()
    @Operation(summary = "숙소 상세 태그타입 조회", description = "해당 숙소에 등록된 태그타입을 조회합니다.")
    ResponseDto<List<TagTypeReadDto>> getTagTypesByHome(@RequestParam Long homeId) {
        return ResponseDto.of(HttpStatus.OK, "조회 성공", tagTypeService.getTagTypeByHouse(homeId));
    }


    @PutMapping("/{tagTypeId}")
    @Secured(UserRoleEnum.Authority.ADMIN)
    @Operation(summary = "태그 타입 수정", description = "태그타입을 수정합니다.")
    ResponseDto<TagTypeResponseDto> putTagType(@PathVariable Long tagTypeId,@RequestBody TagTypeRequestDto tagTypeRequestDto){
        TagTypeResponseDto trd = tagTypeService.putTagType(tagTypeRequestDto,tagTypeId);
        return ResponseDto.of(HttpStatus.OK,"태그 타입 수정에 성공하였습니다",trd);
    }

    @DeleteMapping("/{tagTypeId}")
    @Secured(UserRoleEnum.Authority.ADMIN)
    @Operation(summary = "태그 타입 삭제", description = "태그타입을 삭제합니다.")
    ResponseDto deleteTagType(@PathVariable Long tagTypeId){
        tagTypeService.deleteTagType(tagTypeId);
        return ResponseDto.of(HttpStatus.OK, "삭제 성공");
    }
}
