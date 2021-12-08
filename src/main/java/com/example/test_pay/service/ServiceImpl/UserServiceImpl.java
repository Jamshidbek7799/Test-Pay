package com.example.test_pay.service.ServiceImpl;

import com.example.test_pay.entity.TestEntity;
import com.example.test_pay.entity.UserEntity;
import com.example.test_pay.model.CreateUserDto;
import com.example.test_pay.model.UserDto;
import com.example.test_pay.repository.TestRepository;
import com.example.test_pay.repository.UserRepository;
import com.example.test_pay.role.Role;
import com.example.test_pay.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRepository testRepository;

    @Override
    public UserDto openTeacherAccount(CreateUserDto dto) {
        dto.setRole(Role.TEACHER);

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(dto, userEntity);
        userRepository.save(userEntity);

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        userDto.setId(userEntity.getId());
        return userDto;
    }


    // get all user
    @Override
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDto> dtos = new ArrayList<>();
        userEntities.forEach(userEntity -> {
            UserDto dto = new UserDto();
            BeanUtils.copyProperties(userEntity,dto);
            dto.setId(userEntity.getId());
            dtos.add(dto);
        });

        return ResponseEntity.ok(dtos);
    }

    @Override
    public UserDto openAccountStudent(CreateUserDto dto) {
        dto.setRole(Role.STUDENT);

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(dto, userEntity);
        userRepository.save(userEntity);

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        userDto.setId(userEntity.getId());
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto dto) {
        Optional<UserEntity> optional = userRepository.findById(dto.getId());
        if (!optional.isPresent())
            return null;

        optional.get().setRole(dto.getRole());
        optional.get().setUserName(dto.getUserName());
        optional.get().setBalance(dto.getBalance());
        optional.get().setPhone(dto.getPhone());
        optional.get().setPassword(dto.getPassword());

        userRepository.save(optional.get());
        return dto;
    }

    @Override
    public Object getTest(Long studentId, Long testId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(studentId);
        Optional<TestEntity> testEntityOptional = testRepository.findById(testId);

        if (!userEntityOptional.isPresent())
            return ResponseEntity.ok("not found user " + studentId);
        if (!testEntityOptional.isPresent())
            return ResponseEntity.ok("not found test " + testId);


        UserEntity userEntity = userEntityOptional.get();
        TestEntity testEntity = testEntityOptional.get();

        if (userEntity.getBalance().compareTo(testEntity.getPrice()) <= 0)
            return ResponseEntity.ok("You don't have enough Money: "+ userEntity.getBalance());


        userEntity.setBalance(userEntity.getBalance().subtract(testEntity.getPrice()));

        Optional<UserEntity> optionalUserEntity = userRepository.findById(testEntity.getTeacher_Id());
        if(optionalUserEntity.isPresent()){
            UserEntity teacher = optionalUserEntity.get();
            teacher.setBalance(teacher.getBalance().add(testEntity.getPrice()));
            userRepository.save(teacher);
        }

        userRepository.save(userEntity);



        return ResponseEntity.ok("Payment was made Successfully.\nYour money: "+userEntity.getBalance());
    }
}
