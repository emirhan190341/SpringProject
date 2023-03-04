package com.example.questapp.rules;

import com.example.questapp.repos.PostRepository;
import com.example.questapp.utilites.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostBusinessRules {

    private final PostRepository postRepository;


    public void checkIfPostIdExists(Long postId) {
        if (!this.postRepository.existsPostById(postId)) {
            throw new BusinessException("post with id [%s] not found.".formatted(postId));
        }
    }

}
