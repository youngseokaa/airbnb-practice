package com.example.airbnbpractice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Timestamped {

    @CreatedDate
    @Column(updatable = false)
    @Schema(example = "Thu Mar 02 14:07:04 KST 2023", description = "생성일")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    @Schema(example = "Thu Mar 02 14:07:04 KST 2023", description = "수정일")
    private LocalDateTime modifiedAt;
}