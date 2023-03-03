package com.example.airbnbpractice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "houseTags")
@Getter
@NoArgsConstructor
public class HouseTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(targetEntity = House.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id", insertable = false, updatable = false)
    private House house;

    @Column(name = "house_id")
    private Long houseId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_type_id", insertable = false, updatable = false)
    private HouseTagType tagType;

    @Column(name = "tag_type_id")
    private Long tagTypeId;
}
