package com.example.demo.dto;

import com.example.demo.model.UserEntity;
import com.example.demo.model.UserEntity.UserEntityBuilder;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCreateRequestDto {

    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Age is required")
    private Integer age;

    // @Email(message = "Invalid email address")
    @NotEmpty(message = "Email is required")
    private String email;

    // 기본 생성자, getter 및 setter 생략
    public UserEntity toEntity(){
        return UserEntity.builder().name(name).age(age).email(email).build();
    }
}
