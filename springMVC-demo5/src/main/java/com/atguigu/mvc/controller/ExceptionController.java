package com.atguigu.mvc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

//    註釋的value為要處理的exception !class array!
    @ExceptionHandler(value = {ArithmeticException.class, NullPointerException.class})
    public String testExceptionAnno(Exception ex, Model model){  //方法參數為當前出現的異常
        model.addAttribute("ex", ex);
        System.out.println("anno");
        return "error";
    }
}
