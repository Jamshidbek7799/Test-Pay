package com.example.test_pay.model;

import com.example.test_pay.entity.CurrentEnum;
import lombok.Data;

@Data
public class QuestionCreateDto {
    private String text;
    private String ansA;
    private String ansB;
    private String ansC;
    private CurrentEnum currentAnswer;
    private Long test_id;
}
