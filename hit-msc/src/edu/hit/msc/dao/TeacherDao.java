/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:26:53 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:26:53 
 */
package edu.hit.msc.dao;

import edu.hit.msc.db.DBhelper;
import edu.hit.msc.entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TeacherDao {
    private Connection conn;

    public TeacherDao() {
        try {
            conn = DBhelper.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addTeacher(Teacher teacher) throws Exception {
        String sql = "insert into teacher values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, teacher.getTe_no());
        pst.setString(2, teacher.getTe_name());
        pst.setString(3, teacher.getPassword());
        pst.setString(4, teacher.getDepartment());
        pst.setInt(5, teacher.getWage());
        pst.setDate(6, (java.sql.Date) teacher.getEntry_time());
        pst.setString(7, teacher.getEmail());
        pst.setString(8, teacher.getPhonenumber());
        pst.setString(9, teacher.getJob_title());
        int n = 0;
        try {
            n = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n;
    }

    public int updateTeacher(Teacher teacher) throws Exception {

        String sql = "update teacher set te_name=?,password=?,department=?,wage=?," +
                "entry_time=?,email=?,phonenumber=?,job_title=? where te_no=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, teacher.getTe_name());
        pst.setString(2, teacher.getPassword());
        pst.setString(3, teacher.getDepartment());
        pst.setInt(4, teacher.getWage());
        pst.setDate(5, (java.sql.Date) teacher.getEntry_time());
        pst.setString(6, teacher.getEmail());
        pst.setString(7, teacher.getPhonenumber());
        pst.setString(8, teacher.getJob_title());
        pst.setString(9, teacher.getTe_no());
        int n = pst.executeUpdate();
        return n;

    }

    public List<Teacher> findTeacherByTeNoOrSubNo(String te_no, String sub_no) throws SQLException {
        String sql = "select distinct t.te_no,t.te_name,t.password,t.department,t.wage," +
                "t.entry_time,t.email,t.phonenumber,t.job_title" +
                "  from teacher t,subject sub ";
        StringBuffer sb = new StringBuffer(sql);
        PreparedStatement pst;
        if (te_no != null) {
            sb.append(" where t.te_no=?");
            pst = conn.prepareStatement(sb.toString());

            pst.setString(1, te_no);
        } else {
            sb.append(" where sub.sub_no=?");
            pst = conn.prepareStatement(sb.toString());
            pst.setString(1, sub_no);
        }
        ResultSet res = pst.executeQuery();
        List<Teacher> teacherList = new LinkedList<Teacher>();
        while (res.next()) {
            Teacher teacher = new Teacher(
                    res.getString("te_no"),
                    res.getString("te_name"),
                    res.getString("password"),
                    res.getString("department"),
                    res.getInt("wage"),
                    res.getDate("entry_time"),
                    res.getString("email"),
                    res.getString("phonenumber"),
                    res.getString("job_title")
            );
            teacherList.add(teacher);
        }
        return teacherList;
    }

    public int deleteTeacher(String te_no) throws SQLException {
        String sql = "delete from teacher where te_no=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, te_no);
        int n = pst.executeUpdate();
        return n;
    }

    public boolean updatePassword(String te_no, String oldPassword, String newPassword, String confirm_password)
            throws SQLException {
        String sql = "select password from teacher where te_no = ?";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, te_no);
        ResultSet res = pst.executeQuery();
        String oldPasswordFromDb = null;
        while (res.next()) {
            oldPasswordFromDb = res.getString("password");
        }
        if (oldPassword.equals(oldPasswordFromDb) && newPassword.equals(confirm_password)) {
            sql = "update teacher set password=? where te_no=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, newPassword);
            pst.setString(2, te_no);
            int n = pst.executeUpdate();
            if (n == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
