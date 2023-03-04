package com.example.questapp.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPostResponse {


    private Long id;
    private Long userId;
    private String title;
    private String text;
}
