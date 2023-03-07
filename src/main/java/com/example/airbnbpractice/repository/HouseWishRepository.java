package com.example.airbnbpractice.repository;

import com.example.airbnbpractice.entity.HouseWish;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface HouseWishRepository extends JpaRepository<HouseWish, Long> {
    Optional<HouseWish> findByHouseIdAndUserId(Long houseId, Long userId);

    void deleteByUserIdAndHouseId(Long userId, Long houseId);


}
