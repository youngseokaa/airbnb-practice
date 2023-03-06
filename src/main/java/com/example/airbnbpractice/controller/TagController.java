package com.example.airbnbpractice.controller;

import com.example.airbnbpractice.common.dto.ResponseDto;
import com.example.airbnbpractice.common.security.UserDetailsImpl;
import com.example.airbnbpractice.dto.HouseResponseDto;
import com.example.airbnbpractice.dto.TagResponseDto;
import com.example.airbnbpractice.dto.TagRequestDto;
import com.example.airbnbpractice.dto.TagTypeRequestDto;
import com.example.airbnbpractice.dto.TagTypeResponseDto;
import com.example.airbnbpractice.entity.UserRoleEnum;
import com.example.airbnbpractice.service.TagService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tag")
@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping(value = "/{tagTypeId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Secured(UserRoleEnum.Authority.ADMIN)
    public ResponseDto<TagResponseDto> addTag(@PathVariable Long tagTypeId,
                                              @ModelAttribute TagRequestDto tagRequestDto){
        TagResponseDto td = tagService.addTag(tagTypeId, tagRequestDto);
        return ResponseDto.of(HttpStatus.OK,"태그 등록에 성공하였습니다",td);
    }

    @GetMapping()
    @SecurityRequirements()
    public ResponseDto<List<TagResponseDto>> getTags() {
        return ResponseDto.of(HttpStatus.OK,"조회 성공", tagService.getTags());
    }
}
