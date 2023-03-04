package com.example.questapp.repos;

import com.example.questapp.dto.responses.GetPostResponse;
import com.example.questapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    boolean existsPostById(Long postId);

    List<Post> findPostsByUserId(Long userId);
}
