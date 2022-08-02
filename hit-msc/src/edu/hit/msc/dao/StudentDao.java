/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:26:40 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:26:40 
 */
package edu.hit.msc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.hit.msc.db.DBhelper;
import edu.hit.msc.entity.Student;

public class StudentDao {

    private Connection conn;

    public StudentDao() {
        try {
            conn = DBhelper.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public boolean addStudent(Student stu) throws SQLException {
        String sql = "insert into \n" +
                "student (`stu_no`, `stu_name`, `password`, `class`, `major`, `admissiontime`, `sex`, `department`) \n" +
                "values(?,?,?,?,?,?,?,?)  ";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, stu.getStu_no());
        pst.setString(2, stu.getStu_name());
        pst.setString(3, "123");
        pst.setString(4, stu.getClazz());
        pst.setString(5, stu.getMajor());
        pst.setDate(6, (java.sql.Date) stu.getAdmissiontime());
        pst.setString(7, stu.getSex());
        pst.setString(8, stu.getDepartment());
        int n = pst.executeUpdate();

        if (n == 0)
            return false;
        else
            return true;
    }

    public boolean deleteStudent(String stu_no) throws SQLException {
        String sql = "delete  from student where stu_no =? ";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, stu_no);
        int n = pst.executeUpdate();
        if (n == 0)
            return false;
        else
            return true;
    }

    public boolean updateStudent(Student stu) throws SQLException {

        String sql = "update student set stu_no=?,stu_name=?,password=?,class=?,major=?," +
                "admissiontime=?,sex=?,department=? where stu_no = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        java.sql.Date sqldate = new java.sql.Date(stu.getAdmissiontime().getTime());
        pst.setString(1, stu.getStu_no());
        pst.setString(2, stu.getStu_name());
        pst.setString(3, stu.getPassword());
        pst.setString(4, stu.getClazz());
        pst.setString(5, stu.getMajor());
        pst.setDate(6, sqldate);
        pst.setString(7, stu.getSex());
        pst.setString(8, stu.getDepartment());
        pst.setString(9, stu.getStu_no());
        int n = pst.executeUpdate();
        if (n == 0)
            return false;
        else
            return true;
    }

    public List<Map<String, Object>> queryGrade(String stu_no)
            throws SQLException {
        String sql = "select s.stu_no,s.stu_name,sub.sub_no,sub.sub_name " +
                ",g.final_grade,g.usual_grade,g.paper_grade" + ", g.comment " +
                "from student s,subject sub,grade g where s.stu_no=g.stu_no and " +
                "sub.sub_no=g.sub_no and s.stu_no = ?";
        List<Map<String, Object>> gradeList = new LinkedList<Map<String, Object>>();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, stu_no);
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            Map<String, Object> tem = new HashMap<String, Object>();
            tem.put("stu_no", res.getString("stu_no"));
            tem.put("stu_name", res.getString("stu_name"));
            tem.put("sub_no", res.getString("sub_no"));
            tem.put("sub_name", res.getString("sub_name"));
            tem.put("usual_grade", res.getDouble("usual_grade"));
            tem.put("paper_grade", res.getDouble("paper_grade"));
            tem.put("final_grade", res.getDouble("final_grade"));
            tem.put("comment", res.getString("comment"));
            gradeList.add(tem);
        }
        return gradeList;
    }


    public List<Student> findStudentBysubNoOrTeNo(String te_no, String sub_no)
            throws SQLException {
        String sql;
        if(sub_no == null|| "".equals(sub_no)){
            sql = "select * from student";
        }
        else {
            sql = "select s.stu_no,s.stu_name,s.class,s.major,s.admissiontime,s.sex," +
                    "s.department  from student s where s.stu_no in (select stu_no from stu_te_sub sts where  ";
        }
        StringBuilder sb = new StringBuilder(sql);
        if (te_no != null) {
            sb.append("sts.te_no =?");
            if (sub_no != null)
                sb.append(" and sts.sub_no=?");
        } else
            sb.append("sts.sub_no=?");
        sb.append(")");
        PreparedStatement pst = conn.prepareStatement(sb.toString());
        if (te_no != null) {
            pst.setString(1, te_no);
            if (sub_no != null)
                pst.setString(2, sub_no);
        } else
            pst.setString(1, sub_no);
        ResultSet res = pst.executeQuery();
        List<Student> studentList = new LinkedList<Student>();
        while (res.next()) {
            Student stu = new Student();
            stu.setAdmissiontime((java.util.Date) res.getDate("admissiontime"));
            stu.setClazz(res.getString("class"));
            stu.setDepartment(res.getString("department"));
            stu.setMajor(res.getString("major"));
            stu.setSex(res.getString("sex"));
            stu.setStu_name(res.getString("stu_name"));
            stu.setStu_no(res.getString("stu_no"));
            studentList.add(stu);
        }
        return studentList;
    }

    public boolean updatePassword(String stu_no, String oldPass1, String newPass1, String confirm_pass) throws SQLException//�޸�����
    {

        String sql = "select password from student where stu_no = ?";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, stu_no);
        ResultSet res = pst.executeQuery();
        String oldPass2 = null;
        while (res.next()) {
            oldPass2 = res.getString("password");
        }
        System.out.println(oldPass1 + " " + oldPass2 + " " + newPass1 + " " + confirm_pass + " " + stu_no);
        if (oldPass1.equals(oldPass2)) {
            if (newPass1.equals(confirm_pass)) {
                sql = "update student set password=? where stu_no=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, newPass1);
                pst.setString(2, stu_no);
                int n = pst.executeUpdate();
                if (n == 1)
                    return true;
                else
                    return false;
            } else {
                return false;
            }
        } else
            return false;
    }


    public List<Map<String, Object>> querysubject(String stu_no)
            throws SQLException {
        String sql = "select sub.sub_no,sub.sub_name, sub.open_department, " +
                "te.te_name, sub.paper_grade_per  " +
                "from subject sub,stu_te_sub sts,teacher te  where  " +
                "sts.stu_no = ? and sub.sub_no = sts.sub_no and te.te_no=sts.te_no";
        List<Map<String, Object>> subjectList = new LinkedList<Map<String, Object>>();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, stu_no);
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            Map<String, Object> tem = new HashMap<String, Object>();
            tem.put("sub_no", res.getString("sub_no"));
            tem.put("sub_name", res.getString("sub_name"));
            tem.put("open_department", res.getString("open_department"));
            tem.put("te_name", res.getString("te_name"));
            tem.put("paper_grade_per", (int) (res.getFloat("paper_grade_per") * 100));
            System.out.println(res.getFloat("paper_grade_per") * 100);
            tem.put("usual_grade_per", (100 - (int) (100 * res.getFloat("paper_grade_per"))));
            subjectList.add(tem);
        }
        return subjectList;
    }


    public List<Student> queryOneInfo(String stu_no, String te_no) throws SQLException {
        PreparedStatement pst = null;
        String sql = null;
        if (stu_no.equals("")) {
            sql = "select distinct s.password, s.stu_no,s.stu_name,s.class,s.major,s.admissiontime,"
                    + "s.sex,s.department from student s ,stu_te_sub sts where sts.te_no=? and "
                    + "s.stu_no=sts.stu_no";
            pst = conn.prepareStatement(sql);
            pst.setString(1, te_no);
        } else {
            sql = "select * from student where stu_no=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, stu_no);
        }
        List<Student> stuList = new LinkedList<Student>();
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            Student stu = new Student();
            stu.setAdmissiontime((java.util.Date) res.getDate("admissiontime"));
            stu.setClazz(res.getString("class"));
            stu.setDepartment(res.getString("department"));
            stu.setMajor(res.getString("major"));
            stu.setSex(res.getString("sex"));
            stu.setStu_name(res.getString("stu_name"));
            stu.setStu_no(res.getString("stu_no"));
            stu.setPassword(res.getString("password"));
            stuList.add(stu);
        }
        return stuList;
    }
}





