package com.example.airbnbpractice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "HouseWishes")
@Getter
@NoArgsConstructor
public class HouseWish extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id", insertable = false, updatable = false)
    private House house;

    @Column(name = "house_id")
    private Long houseId;

    public HouseWish(User user, House house) {
        this.user = user;
        this.house = house;
        this.userId = user.getId();
        this.houseId = house.getId();
    }
}
