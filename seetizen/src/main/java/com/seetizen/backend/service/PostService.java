package com.seetizen.backend.service;

import com.seetizen.backend.dto.NearByPostRequest;
import com.seetizen.backend.dto.PostRequest;
import com.seetizen.backend.entity.Image;
import com.seetizen.backend.entity.Post;
import com.seetizen.backend.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;

    public Post createPost(@RequestBody PostRequest postRequest) {
        Post newPost = postRequest.toEntity();
        Image image = imageRepository.findById(postRequest.imageId()).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 이미지입니다."));
        newPost.setImage(image);
        postRepository.save(newPost);
        return newPost;
    }

    public List<Post> getAllPostByCoordinate(NearByPostRequest nearByPostRequest) {
        return postRepository.findAllNearByCoordinate(
                nearByPostRequest.latitude(),
                nearByPostRequest.longitude(),
                1.0);
    }

    public Post likePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("없는 게시글입니다."));
        Integer likes = post.getLikes();
        post.setLikes(likes + 1);
        postRepository.save(post);
        return post;
    }

    public Post dislikePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("없는 게시글입니다."));
        Integer dislikes = post.getDislikes();
        post.setDislikes(dislikes + 1);
        postRepository.save(post);
        return post;
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("없는 게시글입니다."));
    }
}
