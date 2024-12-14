package com.seetizen.backend.controller;

import com.seetizen.backend.dto.ApiResponse;
import com.seetizen.backend.dto.NearByPostRequest;
import com.seetizen.backend.dto.PostRequest;
import com.seetizen.backend.dto.PostResponse;
import com.seetizen.backend.entity.Post;
import com.seetizen.backend.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<ApiResponse<PostResponse>> createPost(@RequestBody PostRequest postRequest) {
        Post newPost = postService.createPost(postRequest);
        return ResponseEntity.ok(ApiResponse.success(newPost.toResponse(), "성공"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostResponse>>> getAllPostByCoordinate(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude) {
        List<Post> posts = postService.getAllPostByCoordinate(latitude, longitude);
        List<PostResponse> postResponses = posts.stream().map(Post::toResponse).toList();
        return ResponseEntity.ok(ApiResponse.success(postResponses, "성공"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(ApiResponse.success(post.toResponse(), "성공"));
    }

    @PatchMapping("/{id}/likes")
    public ResponseEntity<ApiResponse<PostResponse>> likePost(@PathVariable("id") Long postId) {
        Post post = postService.likePost(postId);
        return ResponseEntity.ok(ApiResponse.success(post.toResponse(), "성공"));
    }

    @PatchMapping("/{id}/dislikes")
    public ResponseEntity<ApiResponse<PostResponse>> dislikePost(@PathVariable("id") Long postId) {
        Post post = postService.dislikePost(postId);
        return ResponseEntity.ok(ApiResponse.success(post.toResponse(), "성공"));
    }
}
