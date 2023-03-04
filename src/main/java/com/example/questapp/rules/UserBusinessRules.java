package com.example.questapp.rules;

import com.example.questapp.repos.UserRepository;
import com.example.questapp.utilites.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserBusinessRules {

    private final UserRepository userRepository;

    public void checkIfUserNameExists(String userName) {
        if (this.userRepository.existsUserByUserName(userName)) {
            throw new BusinessException("User already exists.");
        }
    }

    public void checkIfUserIdExists(Long userId) {
        if (!this.userRepository.existsUserById(userId)) {
            throw new BusinessException("user with id [%s] not found.".formatted(userId));
        }
    }



}
