package com.example.test_pay.controller;


import com.example.test_pay.model.CreateUserDto;
import com.example.test_pay.model.UserDto;
import com.example.test_pay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/api")
public class UserController {

    @Autowired
    private  UserService userService;


    public UserController(UserService service) {
        this.userService = service;
    }


    @PostMapping("/openAccount/teacher")
    ResponseEntity<?> openTeacherAccount(@RequestBody CreateUserDto dto){
        UserDto userDto = userService.openTeacherAccount(dto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/openAccount/student")
    ResponseEntity<?> openAccountStudent(@RequestBody CreateUserDto dto){
        UserDto userDto = userService.openAccountStudent(dto);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/getuser/student")
    ResponseEntity<List<UserDto>> getUser() {
        return userService.getUsers();
    }

    @PutMapping("/update/student")
    ResponseEntity<?> updateUser(@RequestBody UserDto dto){
        UserDto userDto = userService.updateUser(dto);
        if(userDto == null)
            return ResponseEntity.ok("NOT FOUND USER");
        return ResponseEntity.ok(" SUCCESSFULY ");
    }

    @GetMapping("/getTest/student")
    ResponseEntity<?> getTest( @RequestParam("studentId") Long studentId,
                               @RequestParam("testId") Long testId){
        return (ResponseEntity<?>) userService.getTest(studentId, testId);
    }



}
