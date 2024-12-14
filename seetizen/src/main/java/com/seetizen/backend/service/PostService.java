package com.seetizen.backend.service;

import com.seetizen.backend.dto.NearByPostRequest;
import com.seetizen.backend.dto.PostRequest;
import com.seetizen.backend.entity.Post;
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

    public Post createPost(@RequestBody PostRequest postRequest) {
        Post newPost = postRequest.toEntity();
        // todo: 이미지 가져오기
        postRepository.save(newPost);
        return newPost;
    }

    public List<Post> getAllPostByCoordinate(NearByPostRequest nearByPostRequest) {
        return postRepository.findAllNearByCoordinate(
                nearByPostRequest.latitude(),
                nearByPostRequest.longitude(),
                1.0);
    }
}
