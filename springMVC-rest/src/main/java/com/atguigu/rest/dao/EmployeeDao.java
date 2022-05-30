package com.atguigu.rest.dao;

import com.atguigu.rest.bean.EmployeeBean;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    private static Map<Integer, EmployeeBean> employees = null;

    static {
        employees = new HashMap<Integer, EmployeeBean>();
        employees.put(1001, new EmployeeBean("Ike", 1001, "ike@aaa.com", 1));
        employees.put(1002, new EmployeeBean("Shu", 1002, "shu@bbb.com", 1));
        employees.put(1003, new EmployeeBean("Mysta", 1003, "mysta@ccc.com", 1));
        employees.put(1004, new EmployeeBean("Vox", 1004, "vox@ddd.com", 0));
        employees.put(1005, new EmployeeBean("Luca", 1005, "luca@eee.com", 0));
    }

    private static Integer initId = 1006;

    //表示insert and update
    public void save(EmployeeBean emp){
        if (emp.getId() == null){
            emp.setId(initId++);
        }

        employees.put(emp.getId(), emp);
    }

    public Collection<EmployeeBean> getAll(){
        return employees.values();
    }

    public EmployeeBean get(Integer id){
        return employees.get(id);
    }

    public void delete(Integer id){
        employees.remove(id);
    }
}
