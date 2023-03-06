package com.example.airbnbpractice.controller;

import com.example.airbnbpractice.common.dto.ResponseDto;
import com.example.airbnbpractice.common.security.UserDetailsImpl;
import com.example.airbnbpractice.dto.*;
import com.example.airbnbpractice.entity.UserRoleEnum;
import com.example.airbnbpractice.service.TagTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagTypeController {

    private final TagTypeService tagTypeService;

    @PostMapping("/tagtype")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public ResponseDto<TagTypeResponseDto> addTagType(@RequestBody TagTypeRequestDto tagTypeRequestDto){
        TagTypeResponseDto trd = tagTypeService.addTagType(tagTypeRequestDto);
        return ResponseDto.of(HttpStatus.OK,"태그 타입 등록에 성공하였습니다",trd);
    }
//
//    @GetMapping("/tagtype")
//    public ResponseDto<List<TagTypeReadDto>> readTagType(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        TagTypeResponseDto trd = tagTypeService.readTagType();
//    }

    @PutMapping("/tagtype/{tagtypeId}")
    @Secured(UserRoleEnum.Authority.ADMIN)
    ResponseDto<TagTypeResponseDto> putTagType(@PathVariable Long tagTypeId,@RequestBody TagTypeRequestDto tagTypeRequestDto){
        TagTypeResponseDto trd = tagTypeService.putTagType(tagTypeRequestDto,tagTypeId);
        return ResponseDto.of(HttpStatus.OK,"태그 타입 수정에 성공하였습니다",trd);
    }

    @DeleteMapping("/tagtype/{tagtypeId}")
    @Secured(UserRoleEnum.Authority.ADMIN)
    ResponseDto deleteTagType(@PathVariable Long tagTypeId){
        tagTypeService.deleteTagType(tagTypeId);
        return ResponseDto.of(HttpStatus.OK, "삭제 성공");
    }
}
