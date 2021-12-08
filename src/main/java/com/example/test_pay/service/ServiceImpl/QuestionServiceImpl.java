package com.example.test_pay.service.ServiceImpl;

import com.example.test_pay.entity.CurrentEnum;
import com.example.test_pay.entity.QuestionEntity;
import com.example.test_pay.entity.TestEntity;
import com.example.test_pay.model.QuestionCreateDto;
import com.example.test_pay.repository.QuestionRepository;
import com.example.test_pay.repository.TestRepository;
import com.example.test_pay.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestRepository testRepository;

    @Override
    public ResponseEntity<?> createQuestion(QuestionCreateDto dto) {
        Optional<TestEntity> optional =testRepository.findById(dto.getTest_id());

        if (!optional.isPresent())
            return ResponseEntity.ok("ERROR GUYS");

        QuestionEntity questionEntity = new QuestionEntity(dto);

        System.out.println(questionEntity.getAnsA() + " "+questionEntity.getTest_id() + " "+ questionEntity.getText());

        questionRepository.save(questionEntity);

        return ResponseEntity.ok(questionEntity);
    }

    @Override
    public ResponseEntity<?> getTestItem(Long questionId, CurrentEnum answerId) {
        Optional<QuestionEntity> optionalQuestionEntity = questionRepository.findById(questionId);

        if (!optionalQuestionEntity.isPresent())
            return ResponseEntity.ok("not found question " + questionId);

        if (optionalQuestionEntity.get().getCurrentAnswer() == answerId){
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.ok(false);
    }


}
