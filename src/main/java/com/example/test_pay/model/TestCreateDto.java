package com.example.test_pay.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TestCreateDto {

    private BigDecimal price;
    private String title;
    private Long teacherId;

}
