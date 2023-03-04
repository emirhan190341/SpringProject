package com.example.questapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;

@SpringBootApplication
public class QuestappApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestappApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
		return new ModelMapper();
    }


}
