package com.example.test_pay.service;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ParseDataService {
    ResponseEntity<?> excelExport(String type) throws IOException;
}
