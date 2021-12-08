package com.example.test_pay.entity;


import com.example.test_pay.model.CreateUserDto;
import com.example.test_pay.role.Role;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity extends AbstractAuditableEntity<UserEntity, Long> {

    @NotNull
    private Role role;
    @NotNull
    private String userName;
    @NotNull
    private BigDecimal balance = new BigDecimal(0);
    @NotNull
    private String phone;
    @NotNull
    private String password;

    public UserEntity(CreateUserDto dto){
        this.role = dto.getRole();
        this.userName = dto.getUserName();
        this.balance = dto.getBalance();

        Pattern pattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{2}[- .]?\\d{2}$");
        Matcher matcher =pattern.matcher(dto.getPhone());
        if (matcher.matches())
            this.phone = dto.getPhone();

        this.phone = dto.getPhone();
        this.password = dto.getPassword();
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "teacher_Id")
    private List<TestEntity> link = new ArrayList<>();

}
