package com.example.airbnbpractice.repository;

import com.example.airbnbpractice.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Long> {
    List<House> findAllByOwnerId(Long ownerId);


}
