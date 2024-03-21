package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.code.UserErrorCode;
import com.example.demo.dto.UserCreateRequestDto;
import com.example.demo.dto.UserUpdateRequestDto;
import com.example.demo.exception.UserException;
import com.example.demo.model.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getUsers() {
        List<UserEntity> userEntities = userService.getAllUsers();
        return ResponseEntity.ok().body(userEntities);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Validated @RequestBody UserCreateRequestDto userCreateRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new UserException(UserErrorCode.BAD_REQUEST, errorMap);
        } else {
            return ResponseEntity.ok().body(userService.createUser(userCreateRequestDto.toEntity()));
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserNameById(@PathVariable Long userId, @Validated @RequestBody UserUpdateRequestDto userUpdateRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new UserException(UserErrorCode.BAD_REQUEST, errorMap);
        } else {
            return ResponseEntity.ok().body(userService.updateUserNameById(userId, userUpdateRequestDto));
        }
    }
}