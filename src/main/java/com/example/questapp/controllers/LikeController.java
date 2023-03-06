package com.example.questapp.controllers;

import com.example.questapp.dto.requests.CreateLikeRequest;
import com.example.questapp.dto.responses.GetLikeResponse;
import com.example.questapp.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    @GetMapping
    public List<GetLikeResponse> getAllLikes(@RequestParam Optional<Long> userId,
                                             @RequestParam Optional<Long> postId) {
        return likeService.getAllLikesWithParam(userId, postId);
    }

    @PostMapping
    public ResponseEntity<?> createOneLike(@RequestBody CreateLikeRequest createLikeRequest) {

        likeService.createOneLike(createLikeRequest);

        return ResponseEntity.ok(" id with " + createLikeRequest.getId() + " has successfully created.");
    }

    @GetMapping("/{likeId}")
    public GetLikeResponse getOneLike(@PathVariable Long likeId) {
        return likeService.getOneLikeById(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId) {

        likeService.deleteOneLikeById(likeId);
    }

}
