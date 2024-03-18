package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SuccessResponse<T> {

    private Boolean success;
    private String statusCode;
    private String code;
    private T data;

    public SuccessResponse(T data) {
        this.success = true;
        this.statusCode = "200";
        this.code = "OK";
        this.data = data;
    }
}