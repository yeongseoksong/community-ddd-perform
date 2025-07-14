package com.portfolio.community.common.response;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice(basePackages = "com.portfolio.community.view")
public class ExceptionCatcher {



    @ExceptionHandler(value = {MotherException.class})
    public String handleCustomException(MotherException e,HttpServletRequest request, Model model) {
        log.error(e.getMessage(), e);
        model.addAttribute("errorMessage", e.getMessage());

        return "error/400";

    }


    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, HttpServletRequest request, Model model) {

        model.addAttribute("errorMessage", "서버 오류가 발생했습니다.");
        return "error/500";
        }


}
