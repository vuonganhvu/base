package com.anhvv.base.common;

import lombok.Data;

@Data
public class ResponseBase<T> {
    private String status;
    private String messageCode;
    private String description;
    private T result;

    public static ResponseBase build(){
        return new ResponseBase();
    }
    public ResponseBase setStatus(String status) {
        this.status = status;
        return this;
    }

    public ResponseBase setMessageCode(String messageCode) {
        this.messageCode = messageCode;
        return this;
    }

    public ResponseBase setDescription(String description){
        this.description = description;
        return this;
    }

    public ResponseBase<T> setResult(T result){
        this.result = result;
        return this;
    }
}
