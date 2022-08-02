/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:27:10 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:27:10 
 */
package edu.hit.msc.entity;

import java.util.*;

public class Student {

    private String stu_no;
    private String stu_name;
    private String password;
    private String clazz;
    private String major;
    private Date admissiontime;
    private String sex;
    private String department;

    public String getStu_no() {
        return stu_no;
    }

    public void setStu_no(String stu_no) {
        this.stu_no = stu_no;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Date getAdmissiontime() {
        return admissiontime;
    }

    public void setAdmissiontime(Date admissiontime) {
        this.admissiontime = admissiontime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
