package com.example.questapp.rules;

import com.example.questapp.repos.LikeRepository;
import com.example.questapp.utilites.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeBusinessRules {

    private final LikeRepository likeRepository;

    public void checkIfLinkIdExists(Long likeId) {

        if (!this.likeRepository.existsLikeById(likeId)) {
            throw new BusinessException("post with id [%s] not found.".formatted(likeId));
        }
    }
}


