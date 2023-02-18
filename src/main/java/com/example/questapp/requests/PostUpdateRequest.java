package com.example.questapp.requests;

import lombok.Data;

//The idea of creating pojos, we don't want to update some fields
//such as Id column so that we are creating new request for this.


@Data
public class PostUpdateRequest {
    String title;
    String text;
}
