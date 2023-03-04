package com.example.questapp.repos;

import com.example.questapp.dto.responses.GetCommentResponse;
import com.example.questapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findCommentsByUserIdAndPostId(Long userId,Long postId);

    List<Comment> findCommentByUserId(Long userId);

    List<Comment> findCommentByPostId(Long postId);


    boolean existsCommentById(Long commentId);
}
