package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Response<T> {

    private Boolean success;
    private String statusCode;
    private String errorContent;
    private List<String> messages;
    private T data;

    public Response(Boolean success, String statusCode, String errorContent, String messages, T data) {
        this.success = success;
        this.statusCode = statusCode;
        this.errorContent = errorContent;
        this.messages = new ArrayList<>();
        this.messages.add(messages);
        this.data = data;
    }

    public Response(T data) {
        this.success = true;
        this.statusCode = "200";
        this.data = data;
    }
}