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

//    @GetMapping("/tagtype")
//    public ResponseDto<List<TagTypeReadDto>> readTagType(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        List<TagTypeReadDto> trd = tagTypeService.readTagType();
//        return ResponseDto.of(HttpStatus.OK,"태그 타입을 읽어 오는것에 성공 하였습니다",trd);
//    }

    @PutMapping("/tagtype/{tagtypeId}")
    @Secured(UserRoleEnum.Authority.ADMIN)
    ResponseDto<TagTypeResponseDto> putTagType(@PathVariable Long tagtypeId,@RequestBody TagTypeRequestDto tagTypeRequestDto){
        TagTypeResponseDto trd = tagTypeService.putTagType(tagTypeRequestDto,tagtypeId);
        return ResponseDto.of(HttpStatus.OK,"태그 타입 수정에 성공하였습니다",trd);
    }

    @DeleteMapping("/tagtype/{tagtypeId}")
    @Secured(UserRoleEnum.Authority.ADMIN)
    ResponseDto deleteTagType(@PathVariable Long tagtypeId){
        tagTypeService.deleteTagType(tagtypeId);
        return ResponseDto.of(HttpStatus.OK, "삭제 성공");
    }
}
