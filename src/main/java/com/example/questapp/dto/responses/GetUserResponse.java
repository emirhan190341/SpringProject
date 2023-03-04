package com.example.questapp.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {

    private Long id;
    private String userName;
    private String password;
}
