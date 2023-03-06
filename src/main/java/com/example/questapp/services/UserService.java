package com.example.questapp.services;

import com.example.questapp.dto.requests.CreateUserRequest;
import com.example.questapp.dto.requests.UserUpdateRequest;
import com.example.questapp.dto.responses.GetUserResponse;
import com.example.questapp.entities.User;
import com.example.questapp.repos.UserRepository;
import com.example.questapp.rules.UserBusinessRules;
import com.example.questapp.utilites.exception.RequestValidationException;
import com.example.questapp.utilites.exception.ResourceNotFoundException;
import com.example.questapp.utilites.mapper.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;
    private final UserBusinessRules userBusinessRules;

    public List<GetUserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream().map(user -> this.modelMapperService.forResponse()
                .map(user, GetUserResponse.class)).collect(Collectors.toList());
    }


    public void createNewUser(CreateUserRequest createUserRequest) {

        this.userBusinessRules.checkIfUserNameExists(createUserRequest.getUserName());

        User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);

        this.userRepository.save(user);

    }

    public GetUserResponse getOneUserById(Long userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                "user with id [%s] not found.".formatted(userId)
        ));

        return this.modelMapperService.forResponse().map(user,GetUserResponse.class);
    }


    public void updateUser(Long userId, UserUpdateRequest userUpdateRequest) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                "user with id [%s] not found.".formatted(userId)
        ));

        boolean changes = false;

        if (userUpdateRequest.getUserName() != null && !userUpdateRequest.getUserName().equals(user.getUsername())) {
            user.setUserName(userUpdateRequest.getUserName());
            changes = true;
        }

        if (userUpdateRequest.getPassword() != null && !userUpdateRequest.getPassword().equals(user.getPassword())) {
            user.setPassword(userUpdateRequest.getPassword());
            changes = true;
        }

        if(!changes){
            throw new RequestValidationException("no data changes found");
        }

        this.userRepository.save(user);

    }

    public void deleteUserById(Long userId) {

     this.userBusinessRules.checkIfUserIdExists(userId);

     userRepository.deleteById(userId);

    }

}
