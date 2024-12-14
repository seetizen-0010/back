package com.seetizen.backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PostResponse(
        Long id,
        LocalDateTime createdAt,
        String content,
        Double latitude,
        Double longitude,
        Integer likes,
        Integer dislikes,
        List<String> tag,
        String address,
        String imagePath,
        Long imageId
) {
}
