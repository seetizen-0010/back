package com.seetizen.backend.entity;

import com.seetizen.backend.dto.PostResponse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    @CreationTimestamp
    LocalDateTime createdAt = LocalDateTime.now();

    @Column
    String content = "";

    @Column(nullable = false)
    Double latitude;

    @Column(nullable = false)
    Double longitude;

    @Column(columnDefinition = "INT DEFAULT 0")
    Integer likes = 0;

    @Column(columnDefinition = "INT DEFAULT 0")
    Integer dislikes = 0;

    @Column(nullable = false)
    String tag;

    @Column(nullable = false)
    String address;
    //
    // @Column
    // Image image;

    public PostResponse toResponse() {
        return PostResponse.builder()
                .id(id)
                .createdAt(createdAt)
                .content(content)
                .latitude(latitude)
                .longitude(longitude)
                .likes(likes)
                .dislikes(dislikes)
                .tag(splitTags(tag))
                .address(address)
                .build();
    }
    private List<String> splitTags(String concatenatedTags) {
        if (concatenatedTags == null || concatenatedTags.isEmpty()) {
            return List.of();
        }
        return Arrays.stream(concatenatedTags.split(",\\s*")) // ", "로 분리
                .map(tag -> tag.replaceAll("\\[|\\]", "")) // 대괄호 제거
                .toList();
    }

}
