package com.example.airbnbpractice.repository;

import com.example.airbnbpractice.entity.HouseWish;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface HouseWishRepository extends JpaRepository<HouseWish, Long> {
    Optional<Object> findByHouseIdAndUserId(Long houseId, Long userId);
    void deleteHouseWishByHouseIdAndUserId(Long houseId, Long userId);
}
