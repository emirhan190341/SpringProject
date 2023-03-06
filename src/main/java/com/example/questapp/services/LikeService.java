package com.example.questapp.services;

import com.example.questapp.dto.requests.CreateLikeRequest;
import com.example.questapp.dto.responses.GetLikeResponse;
import com.example.questapp.dto.responses.GetPostResponse;
import com.example.questapp.dto.responses.GetUserResponse;
import com.example.questapp.entities.Comment;
import com.example.questapp.entities.Like;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import com.example.questapp.repos.LikeRepository;
import com.example.questapp.rules.LikeBusinessRules;
import com.example.questapp.utilites.exception.ResourceNotFoundException;
import com.example.questapp.utilites.mapper.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final ModelMapperService modelMapperService;
    private final UserService userService;
    private final PostService postService;
    private final LikeBusinessRules likeBusinessRules;


    public List<GetLikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if (userId.isPresent() && postId.isPresent()) {
            list = likeRepository.findLikeByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            list = likeRepository.findLikeByUserId(userId.get());
        } else if (postId.isPresent()) {
            list = likeRepository.findLikeByPostId(postId.get());
        } else
            list = likeRepository.findAll();

        return list.stream().map(like -> this.modelMapperService.forResponse()
                .map(like, GetLikeResponse.class)).collect(Collectors.toList());
    }

    public void createOneLike(CreateLikeRequest createLikeRequest) {

        GetUserResponse user = this.userService.getOneUserById(createLikeRequest.getUserId());
        User user1 = this.modelMapperService.forResponse().map(user, User.class);

        GetPostResponse post = this.postService.getOnePostById(createLikeRequest.getPostId());
        Post post1 = this.modelMapperService.forResponse().map(post, Post.class);

        if (user != null && post != null) {
            Like likeToSave = new Like();
            likeToSave.setId(createLikeRequest.getId());
            likeToSave.setPost(post1);
            likeToSave.setUser(user1);
            likeRepository.save(likeToSave);
        } else {
            throw new ResourceNotFoundException("Some credentials could not be provided.");
        }

    }

    public GetLikeResponse getOneLikeById(Long likeId) {

        Like like = this.likeRepository.findById(likeId).orElseThrow(() -> new ResourceNotFoundException(
                "like with id [%s] not found.".formatted(likeId)
        ));

        return this.modelMapperService.forResponse().map(like, GetLikeResponse.class);
    }

    public void deleteOneLikeById(Long likeId) {
        this.likeBusinessRules.checkIfLinkIdExists(likeId);
        likeRepository.deleteById(likeId);

    }
}
