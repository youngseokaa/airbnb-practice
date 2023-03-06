package com.example.airbnbpractice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "houseTags")
@Getter
@NoArgsConstructor
public class HouseTag extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private House house;


    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

    @Builder
    public HouseTag(House house, Tag tag) {
        this.house = house;
        this.tag = tag;
    }

    public void setHouse(House house) {
        if(this.house != null) {
            this.house.getHouseImages().remove(this);
        }
        this.house = house;
        if(!house.getHouseTags().contains(this)) {
            house.addHouseTag(this);
        }
    }
}
