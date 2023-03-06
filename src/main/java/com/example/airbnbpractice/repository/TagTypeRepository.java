package com.example.airbnbpractice.repository;

import com.example.airbnbpractice.entity.TagType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagTypeRepository extends JpaRepository<TagType, Long> {
}
