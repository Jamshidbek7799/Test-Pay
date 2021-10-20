package com.example.test_pay.model;

import com.example.test_pay.role.Role;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull
    private Long id;
    @NotNull
    private Role role;
    @NotNull
    private String userName;
    @NotNull
    private BigDecimal balance;
    @NotNull
    private String phone;
    @NotNull
    private String password;
}
