package com.example.test_pay.model;

import com.example.test_pay.role.Role;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateUserDto {
    private Role role;
    private String userName;
    private String phone;
    private String password;
    private BigDecimal balance = new BigDecimal(0);
}
