package com.seetizen.backend.controller;

import com.seetizen.backend.dto.ApiResponse;
import com.seetizen.backend.dto.PostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @PostMapping
    public ResponseEntity<ApiResponse<PostDto>> createPost() {
        return ResponseEntity.ok(ApiResponse.success(null, "성공"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostDto>>> getAllPostByCoordinate() {
        return ResponseEntity.ok(ApiResponse.success(null, "성공"));
    }
}
