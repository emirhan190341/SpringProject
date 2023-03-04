package com.example.questapp.rules;

import com.example.questapp.repos.CommentRepository;
import com.example.questapp.utilites.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentBusinessRules {

    private final CommentRepository commentRepository;

    public void checkIfCommentIdExists(Long commentId) {

        if (!this.commentRepository.existsCommentById(commentId)) {
            throw new BusinessException("post with id [%s] not found.".formatted(commentId));
        }

    }

}
