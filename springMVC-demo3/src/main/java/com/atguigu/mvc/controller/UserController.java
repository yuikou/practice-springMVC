package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    /**
     *  使用RESTFul 模擬用戶資源的增刪改查
     *  /user   GET     查詢所有使用者
     *  /user/1   GET     根據user id查詢使用者
     *  /user   POST     新增使用者
     *  /user/1   DELETE    刪除使用者
     *  /user   PUT     更新使用者
     */

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUsers(){
        System.out.println("查詢所有使用者");

        return "success";
    }

    @RequestMapping(value = "/user/{1}", method = RequestMethod.GET)
    private String getUserById(){
        System.out.println("根據ID查詢使用者");

        return "success";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String addUser(String username, String description){
        System.out.println("add user: " + username + ": " + description);

        return "success";
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String updateUser(String username, String description){
        System.out.println("update user: " + username + ": " + description);

        return "success";
    }

}
