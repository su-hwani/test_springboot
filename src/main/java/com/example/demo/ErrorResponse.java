package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private Boolean success;
    private String statusCode;
    private String errorContent;
    private List<String> messages;

    public ErrorResponse(String statusCode, String errorContent) {
        this.success = false;
        this.statusCode = statusCode;
        this.errorContent = errorContent;
        this.messages = null;
    }

    public ErrorResponse(String statusCode, String errorContent, String messages) {
        this.success = false;
        this.statusCode = statusCode;
        this.errorContent = errorContent;
        this.messages = new ArrayList<>();
        this.messages.add(messages);
    }

    public ErrorResponse(String statusCode, String errorContent, List<String> messages) {
        this.success = false;
        this.statusCode = statusCode;
        this.errorContent = errorContent;
        this.messages = messages;
    }
}