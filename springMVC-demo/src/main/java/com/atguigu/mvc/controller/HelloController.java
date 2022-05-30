package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    //請求映射
    @RequestMapping(value = "/")
    public String index(){

        //返回的view名稱(去掉前綴後綴)
        return "index";
    }

    @RequestMapping("/target")
    public String toTarget(){
        return "target";
    }
}
