package com.example.test_pay.controller;

import com.example.test_pay.model.TestCreateDto;
import com.example.test_pay.model.TestDto;
import com.example.test_pay.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/api")
public class TestController {
    @Autowired
    private TestService testService;

    @PostMapping("/add")
    ResponseEntity<?> createUser(@RequestBody TestCreateDto dto){
        return ResponseEntity.ok(testService.createTest(dto));
    }



}
