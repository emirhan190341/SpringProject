package com.example.questapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "post")
@Data
public class Post {

    @Id
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    @Column(columnDefinition = "text")
    private String text;


}
