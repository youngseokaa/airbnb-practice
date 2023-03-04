package com.example.airbnbpractice.entity;

import com.example.airbnbpractice.dto.HouseRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "houses")
@NoArgsConstructor
@Getter
public class House extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String thumbnailUrl;
    private String adminDistrict;
    private String detailAddress;
    private String content;
    private Integer maxPeople;
    private Integer pricePerDay;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HouseImage> houseImages;
    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HouseLike> likeUsers;
    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HouseReport> reports;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HouseTag> houseTags;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;


    @Builder
    public House(HouseRequestDto.HouseAdd dto, String thumbnailUrl, User owner) {
        this.thumbnailUrl = thumbnailUrl;
        this.adminDistrict = dto.getAdminDistrict();
        this.detailAddress = dto.getDetailAddress();
        this.content = dto.getContent();
        this.maxPeople = dto.getMaxPeople();
        this.pricePerDay = dto.getPricePerDay();
        this.owner = owner;
        this.houseImages = new HashSet<>();
        this.houseTags = new HashSet<>();
    }


    public void addHouseImage(HouseImage houseImage) {
        this.houseImages.add(houseImage);
        if (!houseImage.getHouse().equals(this)) {
            houseImage.setHouse(this);
        }
    }


    public void addHouseTag(HouseTag houseTag) {
        this.houseTags.add(houseTag);
        if (!houseTag.getHouse().equals(this)) {
            houseTag.setHouse(this);
        }
    }
}
