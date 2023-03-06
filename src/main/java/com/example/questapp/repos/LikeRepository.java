package com.example.questapp.repos;

import com.example.questapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    List<Like> findLikeByUserIdAndPostId(Long userId,Long postId);
    List<Like> findLikeByUserId(Long userId);
    List<Like> findLikeByPostId(Long postId);

    boolean existsLikeById(Long id);

}
