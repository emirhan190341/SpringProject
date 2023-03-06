package com.example.questapp.dto.responses;

import com.example.questapp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPostsResponse {

    private Long id;
    private User user;
    private String title;
    private String text;

}
