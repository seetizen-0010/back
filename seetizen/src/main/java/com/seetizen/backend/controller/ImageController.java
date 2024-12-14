package com.seetizen.backend.controller;

import com.drew.imaging.ImageProcessingException;
import com.seetizen.backend.dto.ApiResponse;
import com.seetizen.backend.dto.ImageUploadResponse;
import com.seetizen.backend.service.ImageService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<ApiResponse<ImageUploadResponse>> getAddress(@RequestParam("image") MultipartFile image)
            throws ImageProcessingException, IOException {
        return ResponseEntity.ok(imageService.getAddressByImage(image));
    }
}
