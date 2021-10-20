package com.example.test_pay.controller;

import com.example.test_pay.model.QuestionCreateDto;
import com.example.test_pay.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @PostMapping("/addQuestion")
    ResponseEntity<?> createUser(@RequestBody QuestionCreateDto dto){
        return ResponseEntity.ok(questionService.createQuestion(dto));
    }

}
