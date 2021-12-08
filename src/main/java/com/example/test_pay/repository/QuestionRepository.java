package com.example.test_pay.repository;

import com.example.test_pay.entity.CurrentEnum;
import com.example.test_pay.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {


}
