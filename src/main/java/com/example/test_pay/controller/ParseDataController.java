package com.example.test_pay.controller;


import com.example.test_pay.service.ParseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("parser")
public class ParseDataController {

    @Autowired
    private ParseDataService parseDataService;

    @GetMapping()
    public ResponseEntity<?> excelExport(@RequestParam String type) throws SQLException, IOException {
        return parseDataService.excelExport(type);
    }

}
