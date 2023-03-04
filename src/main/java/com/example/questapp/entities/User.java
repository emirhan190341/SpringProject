package com.example.questapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {


    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String password;



}
