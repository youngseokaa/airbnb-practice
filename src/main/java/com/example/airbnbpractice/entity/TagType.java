package com.example.airbnbpractice.entity;

import com.example.airbnbpractice.dto.TagTypeRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "tagTypes")
@Getter
@NoArgsConstructor
public class TagType extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(example = "", description = "")
    private String name;

    @OneToMany(mappedBy = "tagType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tag> tags;

    public TagType (TagTypeRequestDto tagTypeRequestDto){
        this.name = tagTypeRequestDto.getName();
    }
}
