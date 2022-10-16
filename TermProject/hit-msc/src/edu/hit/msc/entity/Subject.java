/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:27:18 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:27:18 
 */
package edu.hit.msc.entity;

public class Subject {

    private String sub_no;
    private String sub_name;
    private String open_department;
    private double paper_grade_per;

    public Subject() {
    }

    public Subject(String sub_no, String sub_name, String open_department, double paper_grade_per) {
        this.sub_no = sub_no;
        this.sub_name = sub_name;
        this.open_department = open_department;
        this.paper_grade_per = paper_grade_per;
    }

    public String getSub_no() {
        return sub_no;
    }

    public void setSub_no(String sub_no) {
        this.sub_no = sub_no;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getOpen_department() {
        return open_department;
    }

    public void setOpen_department(String open_department) {
        this.open_department = open_department;
    }

    public double getPaper_grade_per() {
        return paper_grade_per;
    }

    public void setPaper_grade_per(double paper_grade_per) {
        this.paper_grade_per = paper_grade_per;
    }

}
