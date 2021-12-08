package com.example.test_pay.service;


import com.example.test_pay.entity.CurrentEnum;
import com.example.test_pay.model.QuestionCreateDto;

public interface QuestionService {

    Object createQuestion(QuestionCreateDto dto);


    Object getTestItem(Long questionId, CurrentEnum answerId);
}
