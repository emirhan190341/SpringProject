package com.example.questapp.services;

import com.example.questapp.dto.requests.CreatePostRequest;
import com.example.questapp.dto.requests.PostUpdateRequest;
import com.example.questapp.dto.responses.GetPostResponse;
import com.example.questapp.dto.responses.GetUserResponse;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import com.example.questapp.repos.PostRepository;
import com.example.questapp.rules.PostBusinessRules;
import com.example.questapp.utilites.exception.RequestValidationException;
import com.example.questapp.utilites.exception.ResourceNotFoundException;
import com.example.questapp.utilites.mapper.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapperService modelMapperService;
    private final PostBusinessRules postBusinessRules;
    private final UserService userService;

    public List<GetPostResponse> getAllPosts(Optional<Long> userId) {

        if (userId.isPresent()) {
            List<Post> postList = this.postRepository.findPostsByUserId(userId.get());

            return postList.stream().map(post -> this.modelMapperService.forResponse()
                    .map(post, GetPostResponse.class)).collect(Collectors.toList());
        }

        List<Post> posts = this.postRepository.findAll();

        return posts.stream().map(post -> this.modelMapperService.forResponse()
                .map(post, GetPostResponse.class)).collect(Collectors.toList());

    }


    public GetPostResponse getOnePostById(Long postId) {

        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(
                "post with id [%s] not found.".formatted(postId)
        ));

        return this.modelMapperService.forResponse().map(post, GetPostResponse.class);
    }

    public void createNewPost(CreatePostRequest createPostRequest) {

        GetUserResponse user = userService.getOneUserById(createPostRequest.getUserId());

        if (user == null) {
            throw new ResourceNotFoundException(" user with id [%s] not found.".formatted(createPostRequest.getUserId()));
        }

        Post post = this.modelMapperService.forRequest().map(createPostRequest, Post.class);
        postRepository.save(post);

    }

    public void updatePost(Long postId, PostUpdateRequest postUpdateRequest) {

        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(
                "post with id [%s] not found.".formatted(postId)
        ));

        boolean changes = false;

        if (postUpdateRequest.getText() != null && !postUpdateRequest.getText().equals(post.getText())) {
            post.setText(postUpdateRequest.getText());
            changes = true;
        }

        if (postUpdateRequest.getTitle() != null && !postUpdateRequest.getTitle().equals(post.getTitle())) {
            post.setTitle(postUpdateRequest.getTitle());
            changes = true;
        }

        if(!changes){
            throw new RequestValidationException("no data changes found");
        }

        this.postRepository.save(post);


    }

    public void deletePost(Long postId) {

        this.postBusinessRules.checkIfPostIdExists(postId);

        this.postRepository.deleteById(postId);

    }
}
