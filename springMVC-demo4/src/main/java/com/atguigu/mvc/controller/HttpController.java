package com.atguigu.mvc.controller;

import com.atguigu.mvc.bean.User;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HttpController {

    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody) {
        System.out.println(requestBody);

        return "success";
    }

    @RequestMapping("/testRequestEntity")
    public String testRequestEntity(RequestEntity<String> entity) {
        //當前RequestEntity表示整個表求報文(header+body)
        //request header 裡面的"referer"表示來源頁面
        System.out.println("請求header:" + entity.getHeaders());
        System.out.println("請求body:" + entity.getBody());

        return "success";
    }

    //原生ServletAPI達到response body
    @RequestMapping("/testResponse")
    public void testResponse(HttpServletResponse res) throws IOException {

        res.getWriter().print("Hello ServletAPI");
    }

    @RequestMapping(value = "/testResponseBody", produces = "application/json")
//    @RequestMapping(value = "/testResponseBody", produces = "text/html;charset=utf-8")
//    @RequestMapping(value = "/testResponseBody", produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public String testResponseBody() {

        return "回應ResponseBody";
    }

    @RequestMapping("/testResponseUser")
    @ResponseBody
    public User testResponseUser() {
        return new User("SHU", "AVC123", "aa@dd.com", 23, "男");
    }

    @RequestMapping("/testAjax")
    @ResponseBody
    public String testAjax(String username, String password) {
        System.out.println(username + ":::" + password);

        return "Hello, axios";
    }
}
