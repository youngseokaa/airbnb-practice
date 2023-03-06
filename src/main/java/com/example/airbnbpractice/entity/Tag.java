package com.example.airbnbpractice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "", description = "")
    private String name;
    @Schema(example = "", description = "")

    private String imageURL;


    @ManyToOne(fetch = FetchType.LAZY)
    private TagType tagType;
}
