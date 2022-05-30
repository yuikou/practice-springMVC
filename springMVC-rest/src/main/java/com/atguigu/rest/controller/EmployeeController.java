package com.atguigu.rest.controller;

import com.atguigu.rest.bean.EmployeeBean;
import com.atguigu.rest.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getAllEmployee(Model model){
        model.addAttribute("empList",employeeDao.getAll());

        return "emp_list";
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);

        return "redirect:/employee";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String AddEmp(EmployeeBean bean){
        employeeDao.save(bean);

        return "redirect:/employee";
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public String getEmp(@PathVariable("id") Integer id, Model model){
        model.addAttribute("emp", employeeDao.get(id));

        return "emp_update";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public String updateEmp(EmployeeBean bean, Model model){
        employeeDao.save(bean);
        model.addAttribute("emp", employeeDao.get(bean.getId()));

        return "redirect:/employee";
    }

}
