package com.github.iceant.application.meta.console.storage.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ApiResponse<T> {
    int code;
    T result;
    String message;

    public static <T> ApiResponse<T> of(int code, T value, String message){
        return new ApiResponse<T>().setCode(code).setResult(value).setMessage(message);
    }

    public static <T> ApiResponse<T> ok(int code, T value){
        return new ApiResponse<T>().setCode(code).setResult(value);
    }

    public static <T> ApiResponse<T> ok(T value){
        return new ApiResponse<T>().setCode(200).setResult(value);
    }

    public static <T> ApiResponse<T> fail(int code, String message){
        return new ApiResponse<T>().setCode(code).setMessage(message);
    }

    public static <T> ApiResponse<T> fail(String message){
        return new ApiResponse<T>().setCode(500).setMessage(message);
    }

}