package com.example.test_pay.service.ServiceImpl;

import com.example.test_pay.entity.TestEntity;
import com.example.test_pay.model.TestCreateDto;
import com.example.test_pay.model.TestDto;
import com.example.test_pay.repository.TestRepository;
import com.example.test_pay.service.TestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;


    @Override
    public TestDto createTest(TestCreateDto dto) {
        TestEntity testEntity = new TestEntity(dto);
        testRepository.save(testEntity);
        TestDto testDto = new TestDto();
        BeanUtils.copyProperties(testEntity, testDto);
        return testDto;
    }
}
