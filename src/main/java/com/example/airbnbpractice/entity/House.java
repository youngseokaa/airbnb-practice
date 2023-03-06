package com.example.airbnbpractice.entity;

import com.example.airbnbpractice.dto.HouseRequestDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
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
    @Schema(description = "대표사진입니다. 상세페이지의 큰 사진과 메인페이지에서 숙소 썸네일에 사용됩니다.")
    private String thumbnailUrl;
    @Schema(example = "서울특별시", description = "행정구역태그입니다. 특별시, 광역시는 도와 구분되고, 복수체크를 허용하지 않습니다.")
    private String adminDistrict;
    @Schema(example = "행복로 34 행복타운 101-2101", description = "상세주소를 입력합니다.")
    private String detailAddress;
    @Schema(example = "행복로에서 가장 전망이 좋은 숙소입니다", description = "숙소에 대해 소개하고 싶은 내용을 등록자가 입력합니다.")
    private String content;
    @Schema(example = "10", minimum = "1", maximum = "20", description = "최대 수용인원")
    private Integer maxPeople;
    @Schema(example = "100000", minimum = "100", maximum = "10000000", description = "1박 비용")
    private Integer pricePerDay;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HouseImage> houseImages;
    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HouseWish> wishHouses;
    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HouseReport> reports;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservations;

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


    @AllArgsConstructor
    @Getter
    public enum AdministerEnum {
        //        서울특별시 부산광역시 인천광역시 대구광역시 대전광역시 광주광역시 울산광역시 제주특별시 경기도 강원도 충청남도 충청북도 전라북도 전라남도 경상북도 경상남도

        SEOUL("서울특별시"),
//        @JsonProperty("부산광역시")
        BUSAN("부산광역시"),
//        @JsonProperty("대구광역시")
        DAEGU("대구광역시"),
//        @JsonProperty("광주광역시")
        GWANGJU("광주광역시"),
        @JsonProperty("인천광역시")
        INCHEON("인천광역시"),
        @JsonProperty("대전광역시")
        DAEJEON("대전광역시"),
        @JsonProperty("울산광역시")
        ULSAN("울산광역시"),
        @JsonProperty("제주특별시")
        JEJU("제주특별시"),
        @JsonProperty("경기도")
        GYEONGGI("경기도"),
        @JsonProperty("강원도")
        GANGWON("강원도"),
        @JsonProperty("충청남도")
        CHUNGNAM("충청남도"),
        @JsonProperty("충청북도")
        CHUNGBUK("충청북도"),
        @JsonProperty("전라남도")
        JEOLLANAM("전라남도"),
        @JsonProperty("전라북도")
        JEOLLABUK("전라북도"),
        @JsonProperty("경상남도")
        GYEONGSANGNAM("경상남도"),
        @JsonProperty("경상북도")
        GYEONGSANGBUK("경상북도");

        private final String value;

//        @JsonCreator
//        public static AdministerEnum fromValue(String value) {
//            for (AdministerEnum administerEnum : AdministerEnum.values()) {
//                if (administerEnum.value.equalsIgnoreCase(value)) {
//                    return administerEnum;
//                }
//            }
//            throw new IllegalArgumentException("Invalid house type value: " + value);
//        }

//        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
