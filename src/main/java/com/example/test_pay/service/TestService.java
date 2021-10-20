package com.example.test_pay.service;

import com.example.test_pay.model.TestCreateDto;
import com.example.test_pay.model.TestDto;

public interface TestService {
    TestDto createTest(TestCreateDto dto);
}
