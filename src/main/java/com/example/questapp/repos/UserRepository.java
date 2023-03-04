package com.example.questapp.repos;

import com.example.questapp.dto.responses.GetPostResponse;
import com.example.questapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsUserByUserName(String userName);
    boolean existsUserById(Long userId);


}
