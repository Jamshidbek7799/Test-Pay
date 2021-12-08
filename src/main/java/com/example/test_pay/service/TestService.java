package com.example.test_pay.service;

import com.example.test_pay.model.TestCreateDto;
import org.springframework.http.ResponseEntity;

public interface TestService {
    ResponseEntity<?> createTest(TestCreateDto dto);
}
