package com.example.test_pay.controller;

import com.example.test_pay.entity.CurrentEnum;
import com.example.test_pay.model.QuestionCreateDto;
import com.example.test_pay.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @PostMapping("/addQuestion")
    ResponseEntity<?> createUser(@RequestBody QuestionCreateDto dto){
        return ResponseEntity.ok(questionService.createQuestion(dto));
    }


    @GetMapping("/checkQuestion")
    ResponseEntity<?> getTestItem(@RequestParam("questionId") Long questionId,
                                  @RequestParam("answerId") CurrentEnum answerId){
        return (ResponseEntity<?>) questionService.getTestItem(questionId, answerId);
    }

}
