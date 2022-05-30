package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class ParamController {

    @RequestMapping("/testServletAPI")
    //參數的request表示當前請求
    public String testServletAPI(HttpServletRequest request){
        HttpSession session = request.getSession(); //先獲取session
        String username = request.getParameter("username");
        String pw = request.getParameter("password");

        System.out.println("request params: "+ username+pw);

        return "success";
    }

    @RequestMapping("/testParam")
    public String testParam(
            @RequestParam(value = "user_name", required = false, defaultValue = "hehe") String username, String password,
            String[] hobbies,
            @RequestHeader(value = "Host", required = true) String host,
            @CookieValue("JSESSIONID") String JSESSIONID
    ) {
        System.out.println("一致? "+username+"::"+password);
        System.out.println("hobbies:"+ Arrays.toString(hobbies));
        System.out.println("header:"+host);
        System.out.println("JSESSIONID: "+JSESSIONID);

        return "success";
    }
}
