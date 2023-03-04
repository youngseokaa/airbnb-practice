package com.example.airbnbpractice.repository;

import com.example.airbnbpractice.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}
