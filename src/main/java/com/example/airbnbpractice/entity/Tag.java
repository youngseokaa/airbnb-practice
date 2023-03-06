package com.example.airbnbpractice.entity;

import com.example.airbnbpractice.dto.TagRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HouseTag> houseTags;

    public Tag(TagRequestDto tagRequestDto, String imageURL, TagType tagType){
        this.name = tagRequestDto.getName();
        this.imageURL = imageURL;
        this.tagType = tagType;
    }
}
