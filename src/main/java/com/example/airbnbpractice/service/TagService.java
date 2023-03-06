package com.example.airbnbpractice.service;

import com.example.airbnbpractice.common.CustomClientException;
import com.example.airbnbpractice.common.dto.ErrorMessage;
import com.example.airbnbpractice.dto.TagRequestDto;
import com.example.airbnbpractice.dto.TagResponseDto;
import com.example.airbnbpractice.entity.Tag;
import com.example.airbnbpractice.entity.TagType;
import com.example.airbnbpractice.repository.TagRepository;
import com.example.airbnbpractice.repository.TagTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagTypeRepository tagTypeRepository;
    private final TagRepository tagRepository;
    @Transactional
    public TagResponseDto addTag(Long tagTypeId, TagRequestDto tagRequestDto) {
        TagType tagType = tagTypeRepository.findById(tagTypeId).orElseThrow(()-> CustomClientException.of(ErrorMessage.NO_TAGTYPE));
        Tag tag = tagRepository.save(new Tag(tagRequestDto, tagType));
        return TagResponseDto.of(tag);
    }
}
