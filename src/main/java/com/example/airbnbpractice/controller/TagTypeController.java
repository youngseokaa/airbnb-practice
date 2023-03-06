package com.example.airbnbpractice.controller;

import com.example.airbnbpractice.common.dto.ResponseDto;

import com.example.airbnbpractice.dto.*;
import com.example.airbnbpractice.entity.UserRoleEnum;
import com.example.airbnbpractice.service.TagTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TagType")
@RestController
@RequestMapping("/api/tagType")
@RequiredArgsConstructor
public class TagTypeController {

    private final TagTypeService tagTypeService;

    @PostMapping("/")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public ResponseDto<TagTypeResponseDto> addTagType(@RequestBody TagTypeRequestDto tagTypeRequestDto){
        TagTypeResponseDto trd = tagTypeService.addTagType(tagTypeRequestDto);
        return ResponseDto.of(HttpStatus.OK,"태그 타입 등록에 성공하였습니다",trd);
    }

    @GetMapping("/tagType")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public ResponseDto<List<TagTypeReadDto>> readTagType(){
        List<TagTypeReadDto> trd = tagTypeService.readTagType();
        return ResponseDto.of(HttpStatus.OK,"태그 타입을 읽어 오는것에 성공 하였습니다",trd);
    }

    @PutMapping("/{tagTypeId}")
    @Secured(UserRoleEnum.Authority.ADMIN)
    ResponseDto<TagTypeResponseDto> putTagType(@PathVariable Long tagTypeId,@RequestBody TagTypeRequestDto tagTypeRequestDto){
        TagTypeResponseDto trd = tagTypeService.putTagType(tagTypeRequestDto,tagTypeId);
        return ResponseDto.of(HttpStatus.OK,"태그 타입 수정에 성공하였습니다",trd);
    }

    @DeleteMapping("/{tagTypeId}")
    @Secured(UserRoleEnum.Authority.ADMIN)
    ResponseDto deleteTagType(@PathVariable Long tagTypeId){
        tagTypeService.deleteTagType(tagTypeId);
        return ResponseDto.of(HttpStatus.OK, "삭제 성공");
    }
}
