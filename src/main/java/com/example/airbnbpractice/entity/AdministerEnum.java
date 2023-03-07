package com.example.airbnbpractice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
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
    INCHEON("인천광역시"),
    DAEJEON("대전광역시"),
    ULSAN("울산광역시"),
    JEJU("제주특별시"),
    GYEONGGI("경기도"),
    GANGWON("강원도"),
    CHUNGNAM("충청남도"),
    CHUNGBUK("충청북도"),
    JEOLLANAM("전라남도"),
    JEOLLABUK("전라북도"),
    GYEONGSANGNAM("경상남도"),
    GYEONGSANGBUK("경상북도");

    private final String value;

    public static AdministerEnum fromValue(String value) {
        for (AdministerEnum administerEnum : AdministerEnum.values()) {
            if (administerEnum.value.equalsIgnoreCase(value)) {
                return administerEnum;
            }
        }
        throw new IllegalArgumentException("Invalid house type value: " + value);
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}

