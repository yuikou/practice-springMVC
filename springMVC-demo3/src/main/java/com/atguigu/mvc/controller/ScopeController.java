package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ScopeController {

    //    使用ServletAPI向request scope對象共享數據
    @RequestMapping("/testRequestScopeServletAPI")
    public String testRequestScopeServletAPI(HttpServletRequest req) {

        req.setAttribute("testReq", "Hello, ServletAPI");

        return "success";
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {

        ModelAndView modelAndView = new ModelAndView();
//        處理模型數據, 即向request域共享數據
        modelAndView.addObject("testReq", "Hello, modelAndView");
//        設置view名稱
        modelAndView.setViewName("success");

        return modelAndView;
    }

    @RequestMapping("/testModel")
    public String testModel(Model model){

        model.addAttribute("testReq", "Model!!");
        return "success";
    }

    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map){
        map.put("testReq", "Hello, Map");
        return "success";
    }

    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap modelMap){
        modelMap.addAttribute("testReq", "Hello Modelmap!");

        return "success";
    }

    @RequestMapping("/testSession")
    public String testSession(HttpSession session){
        session.setAttribute("testSessionScope", "Hello Session!");

        return "success";
    }

    @RequestMapping("/testApplication")
    public String testApplication(HttpSession session){
        ServletContext context = session.getServletContext();
        context.setAttribute("testApplicationScope", "Hello Appliction(Context)");

        return "success";
    }
}
