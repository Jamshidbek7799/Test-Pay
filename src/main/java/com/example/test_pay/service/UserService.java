package com.example.test_pay.service;

import com.example.test_pay.model.CreateUserDto;
import com.example.test_pay.model.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    UserDto openTeacherAccount(CreateUserDto dto);

    ResponseEntity<List<UserDto>> getUsers();

    UserDto openAccountStudent(CreateUserDto dto);

    UserDto updateUser(UserDto dto);

    Object getTest(Long studentId, Long testId);
}
