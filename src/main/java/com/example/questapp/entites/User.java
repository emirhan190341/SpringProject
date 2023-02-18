package com.example.questapp.entites;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user")
@Data //It will create getter and setter for our User class
public class User {
    @Id
    Long id;

    String userName;
    String password;
}
