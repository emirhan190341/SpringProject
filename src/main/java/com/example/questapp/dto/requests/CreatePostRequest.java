package com.example.questapp.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {

    private Long id;
    private String text;
    private String title;
    private Long userId;


}
