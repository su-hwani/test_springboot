package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.UserCreateRequestDto;
import com.example.demo.dto.UserUpdateRequestDto;
import com.example.demo.model.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import java.util.List;


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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed: " + bindingResult.getAllErrors());
        } else {
            return ResponseEntity.ok().body(userService.createUser(userCreateRequestDto.toEntity()));
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserNameById(@PathVariable Long userId, @Validated @RequestBody UserUpdateRequestDto userUpdateRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed: " + bindingResult.getAllErrors());
        } else {
            return ResponseEntity.ok().body(userService.updateUserNameById(userId, userUpdateRequestDto));
        }
    }
}