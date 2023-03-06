package com.example.airbnbpractice.repository;

import com.example.airbnbpractice.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByIdIn(Set<Long> ids);
    Set<Tag> findByTagType_Id(Long id);

}
