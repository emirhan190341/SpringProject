package com.example.questapp.dto.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLikeResponse {


    Long id;
    Long userId;
    Long postId;

}
