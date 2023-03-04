package com.example.airbnbpractice.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "houseImages")
@Getter
@NoArgsConstructor
public class HouseImage extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageURL;

    @ManyToOne(fetch = FetchType.LAZY)
    private House house;

    public void setHouse(House house) {
        if(this.house != null) {
            this.house.getHouseImages().remove(this);
        }
        this.house = house;
        if(!house.getHouseImages().contains(this)) {
            house.addHouseImage(this);
        }
    }

    @Builder
    public HouseImage(House house, String imageURL) {
        this.house = house;
        this.imageURL = imageURL;
    }
}
