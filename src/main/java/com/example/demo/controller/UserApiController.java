package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ErrorResponse;
import com.example.demo.Response;
import com.example.demo.SuccessResponse;
import com.example.demo.dto.UserCreateRequestDto;
import com.example.demo.model.UserEntity;
import com.example.demo.model.UserRepository;
import com.example.demo.model.UserEntity.UserEntityBuilder;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/findAll") 
    public List<UserEntity> findAll() {
        return  userRepository.findAll();
    }

    @GetMapping("/name")
    public ResponseEntity<?> autoSave(@RequestParam String name) {
        var user = UserEntity.builder().name(name).build();

        var response = userRepository.save(user);
        return ResponseEntity.status(400).body(new Response(true));
    }

    @PostMapping("/name")
    public ResponseEntity<?> creaUserEntity(@Validated @RequestBody UserCreateRequestDto userCreateRequestDto) {
        try {
            UserEntity newUser = userCreateRequestDto.toEntity();
            UserEntity savedUser = userRepository.save(newUser);
            return ResponseEntity.ok().body(new SuccessResponse(savedUser));
        } catch (DataAccessException e) {
            // 데이터베이스 접근에 실패한 경우
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("500","Failed to create user"));
        } catch (Exception e) {
            // 그 외의 예외가 발생한 경우
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("400", "ㅁㄴㅇ"));
        }
    }
}
