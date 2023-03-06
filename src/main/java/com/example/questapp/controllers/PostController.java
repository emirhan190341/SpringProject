package com.example.questapp.controllers;

import com.example.questapp.dto.requests.CreatePostRequest;
import com.example.questapp.dto.requests.PostUpdateRequest;
import com.example.questapp.dto.requests.UserUpdateRequest;
import com.example.questapp.dto.responses.GetAllPostsResponse;
import com.example.questapp.dto.responses.GetPostResponse;
import com.example.questapp.entities.Post;
import com.example.questapp.services.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    //...posts?userId={userId}
    @GetMapping
    public List<GetPostResponse> getPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody CreatePostRequest createPostRequest) {

        postService.createNewPost(createPostRequest);

        return ResponseEntity.ok(createPostRequest.getId() + " has created successfully.");

    }

    @GetMapping("/{postId}")
    public GetPostResponse getPost(@PathVariable Long postId) {
        return postService.getOnePostById(postId);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(
            @PathVariable Long postId,
            @RequestBody PostUpdateRequest postUpdateRequest
    ) {
        postService.updatePost(postId, postUpdateRequest);

        return ResponseEntity.ok(" with id " + postId + " has successfully updated.");
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId) {

        postService.deletePost(postId);
    }


}
