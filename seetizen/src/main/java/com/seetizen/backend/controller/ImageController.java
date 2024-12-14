package com.seetizen.backend.controller;

import com.seetizen.backend.dto.ApiResponse;
//import com.seetizen.backend.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {

//    private final ImageService imageService;

    public ResponseEntity<ApiResponse<String>> getAddress(@RequestParam("image")MultipartFile image){
//        return ResponseEntity.ok(imageService.getAddress(image));
        return ResponseEntity.ok(ApiResponse.success(null, "asdf"));
    }
}
