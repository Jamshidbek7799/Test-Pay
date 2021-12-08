package com.example.test_pay.entity;

import com.example.test_pay.model.QuestionCreateDto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "questions")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String text;
    @NotNull
    private CurrentEnum currentAnswer;
    @NotNull
    private String ansA;
    @NotNull
    private String ansB;
    @NotNull
    private String ansC;
    @NotNull
    private Long test_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", insertable = false, updatable = false)
    private TestEntity test;

    public QuestionEntity(QuestionCreateDto dto) {
        this.text = dto.getText();
        this.ansA = dto.getAnsA();
        this.ansB = dto.getAnsB();
        this.ansC = dto.getAnsC();
        this.currentAnswer = dto.getCurrentAnswer();
        this.test_id = dto.getTest_id();
    }

}
