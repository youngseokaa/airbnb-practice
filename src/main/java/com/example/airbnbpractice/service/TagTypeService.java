package com.example.airbnbpractice.service;

import com.example.airbnbpractice.common.CustomClientException;
import com.example.airbnbpractice.common.dto.ErrorMessage;
import com.example.airbnbpractice.dto.TagTypeReadDto;
import com.example.airbnbpractice.dto.TagTypeRequestDto;
import com.example.airbnbpractice.dto.TagTypeResponseDto;
import com.example.airbnbpractice.entity.TagType;
import com.example.airbnbpractice.repository.TagRepository;
import com.example.airbnbpractice.repository.TagTypeRepository;
import com.example.airbnbpractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.example.airbnbpractice.common.dto.ErrorMessage.NO_USER;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagTypeRepository tagTypeRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    @Transactional
    public TagResponseDto addTag(Long tagTypeId, TagRequestDto tagRequestDto) {
        TagType tagType = tagTypeRepository.findById(tagTypeId).orElseThrow(()-> CustomClientException.of(ErrorMessage.NULL_TAGTYPE));
        Tag tag = tagRepository.save(new Tag(tagRequestDto, tagType));
        return TagResponseDto.of(tag);
    }

    @Transactional
    public TagTypeResponseDto addTagType(TagTypeRequestDto tagTypeRequestDto) {
        TagType tagType = tagTypeRepository.save(new TagType(tagTypeRequestDto));
        return TagTypeResponseDto.of(tagType);
    }


//    public List<TagTypeReadDto> readTagType() {
//         List<TagType> tagTypes = tagTypeRepository.findAll();
//         List<TagTypeReadDto> tagTypeReadDtos = new ArrayList<>();
//
//    }


    public TagTypeResponseDto putTagType(TagTypeRequestDto tagTypeRequestDto,Long tagTypeId) {
        TagType tagType = tagTypeRepository.findById(tagTypeId).orElseThrow(
                ()->  CustomClientException.of(ErrorMessage.NO_TAGTYPE)
        );
        tagType.update(tagTypeRequestDto);
        return TagTypeResponseDto.of(tagType);
    }


    public void deleteTagType(Long tagTypeId) {
        TagType tagType = tagTypeRepository.findById(tagTypeId).orElseThrow(
                ()->  CustomClientException.of(ErrorMessage.DELETE_REJECT)
        );
        tagTypeRepository.delete(tagType);
    }
}
