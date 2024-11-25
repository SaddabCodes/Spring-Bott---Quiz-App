package com.sadcode.quizapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class QuizAppApplication {

    public static void main(String[] args)  {
        SpringApplication.run(QuizAppApplication.class, args);

    }

}
