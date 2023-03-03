package com.example.airbnbpractice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "houseTagTypes")
@Getter
@NoArgsConstructor
public class HouseTagType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "tagType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HouseTag> tags;
}
