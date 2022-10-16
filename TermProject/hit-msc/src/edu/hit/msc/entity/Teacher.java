/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:27:24 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:27:24 
 */
package edu.hit.msc.entity;

import java.util.Date;

public class Teacher {
    private String te_no;
    private String te_name;
    private String password;
    private String department;
    private int wage;
    private Date entry_time;
    private String email;
    private String phonenumber;
    private String job_title;

    public Teacher() {
    }

    ;

    public Teacher(String te_no, String te_name, String password, String department,
                   int wage, Date entry_time, String email, String phonenumber, String job_title) {
        this.te_no = te_no;
        this.te_name = te_name;
        this.password = password;
        this.department = department;
        this.wage = wage;
        this.entry_time = entry_time;
        this.email = email;
        this.phonenumber = phonenumber;
        this.job_title = job_title;
    }

    public String getTe_no() {
        return te_no;
    }

    public void setTe_no(String te_no) {
        this.te_no = te_no;
    }

    public String getTe_name() {
        return te_name;
    }

    public void setTe_name(String te_name) {
        this.te_name = te_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public Date getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(Date entry_time) {
        this.entry_time = entry_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }
}
