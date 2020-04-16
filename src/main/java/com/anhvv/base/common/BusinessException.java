package com.anhvv.base.common;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private String errorCode;
    private String message;

    public BusinessException(String errorCode, String message) {
        super(errorCode);
        this.errorCode = errorCode;
        this.message = message;
    }
}
