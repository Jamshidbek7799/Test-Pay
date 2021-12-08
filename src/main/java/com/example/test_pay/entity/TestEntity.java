package com.example.test_pay.entity;


import com.example.test_pay.model.TestCreateDto;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "tests")
@Entity
@NoArgsConstructor
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotNull
    private BigDecimal price;

    @NotNull
    private String title;

    @NotNull
    private Long teacher_Id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_Id", insertable = false, updatable = false)
    private UserEntity user;

    public TestEntity(TestCreateDto dto){
        this.price = dto.getPrice();
        this.title = dto.getTitle();
        this.teacher_Id = dto.getTeacherId();
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "test_id")
    private List<QuestionEntity> comments = new ArrayList<>();



}
