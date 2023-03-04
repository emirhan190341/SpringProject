package com.example.questapp.controllers;

import com.example.questapp.dto.requests.CreateUserRequest;
import com.example.questapp.dto.requests.UserUpdateRequest;
import com.example.questapp.dto.responses.GetUserResponse;
import com.example.questapp.services.UserService;
import jakarta.validation.OverridesAttribute;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping
    public List<GetUserResponse> getUsers() {

        return userService.getAllUsers();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {

        userService.createNewUser(createUserRequest);

        return ResponseEntity.ok(createUserRequest.getUserName() + " has successfully created.");

    }

    @GetMapping("/{userId}")
    public GetUserResponse getUser(@PathVariable Long userId) {
        return userService.getOneUserById(userId);
    }

    @PutMapping("/{userId}")
    public void updateUser(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequest userUpdateRequest
    ) {
        userService.updateUser(userId, userUpdateRequest);


    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }


}
