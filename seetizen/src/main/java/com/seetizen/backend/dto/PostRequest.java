package com.seetizen.backend.dto;

import com.seetizen.backend.entity.Post;

import java.math.BigDecimal;
import java.util.List;

public record PostRequest(String content, Double latitude, Double longitude, String address, List<String> tag, Long imageId) {
    public Post toEntity() {
        return Post.builder()
                .content(content)
                .latitude(latitude)
                .longitude(longitude)
                .likes(0)
                .dislikes(0)
                .tag(concatTags(tag))
                .address(address)
                .build();
    }

    private String concatTags(List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return ""; // 태그 리스트가 비어있으면 빈 문자열 반환
        }
        return String.join(", ", tags); // 태그를 ", "로 연결
    }
}
