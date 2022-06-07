package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TestController {

    final static String OK = "success";

    @RequestMapping("/**/testIntercepter")
    public String testIntercepter() {

        return OK;
    }

    @RequestMapping("/testExceptionHandler")
    public String testExceptionHandler() {
        System.out.println(10 / 0);
        return OK;
    }

}
