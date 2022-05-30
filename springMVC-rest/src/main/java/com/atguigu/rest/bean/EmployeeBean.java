package com.atguigu.rest.bean;

public class EmployeeBean {

    public EmployeeBean() {
    }

    public EmployeeBean(String lastname, Integer id, String email, Integer gender) {
        this.lastname = lastname;
        this.id = id;
        this.email = email;
        this.gender = gender;
    }

    private String lastname;
    private Integer id;
    private String email;
    private Integer gender; //1 male, 0 female

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }


}
