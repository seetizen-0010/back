package com.seetizen.backend.dto;

import java.io.File;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ImageUploadResponse {

    private Long imageId;
    private BigDecimal latitude; // 위도, y
    private BigDecimal longitude; // 경도, x
    private String address; // 주소
    private String imagePath;

    public ImageUploadResponse(BigDecimal latitude, BigDecimal longitude, String address,
                               String imagePath) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.imagePath = imagePath;
    }

    public void setId(Long id) {
        this.imageId = id;
    }
}

/**
 * { "success": true, "data": { "latitude": 00.0000, "longtitude": 00.0000, "address": "경기도 성남시 분당구 삼평동 판교역로 160",
 * "image": "/image/path/201341485458475892457245.png" } }
 */