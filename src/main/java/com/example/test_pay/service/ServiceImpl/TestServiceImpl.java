package com.example.test_pay.service.ServiceImpl;

import com.example.test_pay.entity.TestEntity;
import com.example.test_pay.entity.UserEntity;
import com.example.test_pay.model.TestCreateDto;
import com.example.test_pay.repository.TestRepository;
import com.example.test_pay.repository.UserRepository;
import com.example.test_pay.role.Role;
import com.example.test_pay.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseEntity<?> createTest(TestCreateDto dto) {
        Optional<UserEntity> optional = userRepository.findById(dto.getTeacherId());
        TestEntity testEntity = new TestEntity(dto);

        if (!optional.isPresent())
            return ResponseEntity.ok("Teacher "+ dto.getTeacherId() +" not found");

        if (optional.get().getRole().equals(Role.TEACHER)){
            testRepository.save(testEntity);
            return ResponseEntity.ok(testEntity);
        }
        return ResponseEntity.ok("This user is not a Teacher!");
    }
}

