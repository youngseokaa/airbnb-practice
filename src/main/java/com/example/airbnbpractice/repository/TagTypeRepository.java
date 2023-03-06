package com.example.airbnbpractice.repository;

import com.example.airbnbpractice.entity.House;
import com.example.airbnbpractice.entity.TagType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagTypeRepository extends JpaRepository<TagType, Long> {
    List<TagType> findByTags_HouseTags_House(House house);


}
