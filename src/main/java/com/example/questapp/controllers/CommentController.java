package com.example.questapp.controllers;

import com.example.questapp.dto.requests.CommentUpdateRequest;
import com.example.questapp.dto.requests.CreateCommentRequest;
import com.example.questapp.dto.responses.GetCommentResponse;
import com.example.questapp.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<GetCommentResponse> getComments(@RequestParam Optional<Long> userId,
                                                @RequestParam Optional<Long> postId) {
        return commentService.getAllCommentsWithParam(userId, postId);

    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CreateCommentRequest createCommentRequest) {
        commentService.createNewComment(createCommentRequest);

        return ResponseEntity.ok(" with id " + createCommentRequest.getId() + " has successfully created.");
    }

    @GetMapping("/{commentId}")
    public GetCommentResponse getComment(@PathVariable Long commentId) {
        return commentService.getOneComment(commentId);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId,
                                           @RequestBody CommentUpdateRequest commentUpdateRequest) {
        commentService.updateComment(commentId,commentUpdateRequest);

        return ResponseEntity.ok(" with id " + commentId + " has successfully updated.");
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }


}
