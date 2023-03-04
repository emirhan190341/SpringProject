package com.example.questapp.services;

import com.example.questapp.dto.requests.CommentUpdateRequest;
import com.example.questapp.dto.requests.CreateCommentRequest;
import com.example.questapp.dto.responses.GetCommentResponse;
import com.example.questapp.dto.responses.GetPostResponse;
import com.example.questapp.dto.responses.GetUserResponse;
import com.example.questapp.entities.Comment;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import com.example.questapp.repos.CommentRepository;
import com.example.questapp.rules.CommentBusinessRules;
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
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapperService modelMapperService;

    private final UserService userService;
    private final PostService postService;

    private final CommentBusinessRules commentBusinessRules;


    public List<GetCommentResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {

        if (userId.isPresent() && postId.isPresent()) {
            List<Comment> commentList = commentRepository.findCommentsByUserIdAndPostId(userId.get(), postId.get());
            return commentList.stream().map(comment -> this.modelMapperService.forResponse()
                    .map(commentList, GetCommentResponse.class)).collect(Collectors.toList());

        } else if (userId.isPresent()) {
            return commentRepository.findCommentByUserId(userId.get()).stream().map(comment -> this.modelMapperService.forResponse()
                    .map(comment, GetCommentResponse.class)).collect(Collectors.toList());
        } else if (postId.isPresent()) {
            return commentRepository.findCommentByPostId(postId.get()).stream().map(comment -> this.modelMapperService.forResponse()
                    .map(comment, GetCommentResponse.class)).collect(Collectors.toList());
        }

        List<Comment> comments = commentRepository.findAll();

        return comments.stream().map(comment -> this.modelMapperService.forResponse()
                .map(comment, GetCommentResponse.class)).collect(Collectors.toList());
    }

    public GetCommentResponse getOneComment(Long commentId) {

        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException(
                "comment with id [%s] not found.".formatted(commentId)
        ));

        return this.modelMapperService.forResponse().map(comment, GetCommentResponse.class);

    }

    public void createNewComment(CreateCommentRequest createCommentRequest) {

        GetUserResponse user = this.userService.getOneUserById(createCommentRequest.getUserId());
        User user1 = this.modelMapperService.forResponse().map(user, User.class);

        GetPostResponse post = this.postService.getOnePostById(createCommentRequest.getPostId());
        Post post1 = this.modelMapperService.forResponse().map(post, Post.class);

        if (user != null && post != null) {
            Comment commentToSave = new Comment();
            commentToSave.setId(createCommentRequest.getId());
            commentToSave.setPost(post1);
            commentToSave.setUser(user1);
            commentToSave.setText(createCommentRequest.getText());
            commentRepository.save(commentToSave);
        } else {
            throw new ResourceNotFoundException("Some credentials could not be provided.");
        }

    }

    public void updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {

        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException(
                "comment with id [%s] not found.".formatted(commentId)
        ));


        boolean changes = false;

        if (commentUpdateRequest.getText() != null && !commentUpdateRequest.getText().equals(comment.getText())) {
            comment.setText(commentUpdateRequest.getText());
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("no data changes found");
        }

        commentRepository.save(comment);

    }

    public void deleteComment(Long commentId) {
        this.commentBusinessRules.checkIfCommentIdExists(commentId);

        this.commentRepository.deleteById(commentId);


    }
}
