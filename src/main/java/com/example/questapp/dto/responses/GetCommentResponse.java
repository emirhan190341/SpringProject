package com.example.questapp.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCommentResponse {

    private Long id;
    private Long userId;
    private Long postId;
    private String text;

}
