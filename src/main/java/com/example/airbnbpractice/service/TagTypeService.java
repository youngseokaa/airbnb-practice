package com.example.airbnbpractice.service;

import com.example.airbnbpractice.common.CustomClientException;
import com.example.airbnbpractice.common.dto.ErrorMessage;
import com.example.airbnbpractice.dto.TagTypeReadDto;
import com.example.airbnbpractice.dto.TagTypeRequestDto;
import com.example.airbnbpractice.dto.TagTypeResponseDto;
import com.example.airbnbpractice.entity.House;
import com.example.airbnbpractice.entity.TagType;
import com.example.airbnbpractice.repository.HouseRepository;
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
public class TagTypeService {

    private final TagTypeRepository tagTypeRepository;
    private final HouseRepository houseRepository;



    @Transactional
    public TagTypeResponseDto addTagType(TagTypeRequestDto tagTypeRequestDto) {
        TagType tagType = tagTypeRepository.save(new TagType(tagTypeRequestDto));
        return TagTypeResponseDto.of(tagType);
    }

    @Transactional(readOnly = true)
    public List<TagTypeReadDto> readTagType() {
        return tagTypeRepository.findAll().stream().map(TagTypeReadDto::of).toList();
    }

    public List<TagTypeReadDto> getTagTypeByHouse(Long houseId) {
        House house = houseRepository.findById(houseId).orElseThrow(
                () -> CustomClientException.of(ErrorMessage.NO_HOUSE)
        );
        return tagTypeRepository.findByTags_HouseTags_House(house).stream().map(TagTypeReadDto::of).toList();
    }

    @Transactional
    public TagTypeResponseDto putTagType(TagTypeRequestDto tagTypeRequestDto,Long tagTypeId) {
        TagType tagType = tagTypeRepository.findById(tagTypeId).orElseThrow(
                ()->  CustomClientException.of(ErrorMessage.NO_TAGTYPE)
        );
        tagType.update(tagTypeRequestDto);
        return TagTypeResponseDto.of(tagType);
    }

    @Transactional
    public void deleteTagType(Long tagTypeId) {
        TagType tagType = tagTypeRepository.findById(tagTypeId).orElseThrow(
                ()->  CustomClientException.of(ErrorMessage.DELETE_REJECT)
        );
        tagTypeRepository.delete(tagType);
    }
}
