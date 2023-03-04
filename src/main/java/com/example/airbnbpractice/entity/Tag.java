package com.example.airbnbpractice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "tags")
@Getter
@NoArgsConstructor
public class Tag extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String imageURL;


    @ManyToOne(fetch = FetchType.LAZY)
    private TagType tagType;
}
