package com.portfolio.community.common.response;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MotherException extends RuntimeException {

    private final HttpStatus httpStatus;

    public MotherException(String message){
        this(message, HttpStatus.BAD_REQUEST);
    }

    public MotherException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus=httpStatus;
    }


}
