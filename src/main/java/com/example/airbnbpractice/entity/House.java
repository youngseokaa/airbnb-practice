package com.example.airbnbpractice.entity;

import com.example.airbnbpractice.dto.HouseRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

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
    private Set<HouseWish> likeUsers;
    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HouseReport> reports;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HouseTag> houseTags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private User owner;

    @Column(name = "owner_id")
    private Long ownerId;



    @Builder
    public House(HouseRequestDto.HouseAdd dto, String thumbnailUrl, Long ownerId) {
        this.thumbnailUrl = thumbnailUrl;
        this.adminDistrict = dto.getAdminDistrict();
        this.detailAddress = dto.getDetailAddress();
        this.content = dto.getContent();
        this.maxPeople = dto.getMaxPeople();
        this.pricePerDay = dto.getPricePerDay();
        this.ownerId = ownerId;
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

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public House update(HouseRequestDto.HouseUpdate dto) {
        this.adminDistrict = ObjectUtils.defaultIfNull(dto.getAdminDistrict(), this.adminDistrict);
        this.content = ObjectUtils.defaultIfNull(dto.getContent(), this.content);
        this.detailAddress = ObjectUtils.defaultIfNull(dto.getDetailAddress(), this.detailAddress);
        this.maxPeople = ObjectUtils.defaultIfNull(dto.getMaxPeople(), this.maxPeople);
        this.pricePerDay = ObjectUtils.defaultIfNull(dto.getPricePerDay(), this.pricePerDay);

        return this;
    }
}
