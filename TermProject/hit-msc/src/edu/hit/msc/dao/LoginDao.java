/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:26:33 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:26:33 
 */
package edu.hit.msc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import edu.hit.msc.db.DBhelper;

public class LoginDao {

    private Connection conn;

    public LoginDao() {
        try {
            conn = DBhelper.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Map<String, String> setStudentSession(String id, String password, String userType)
            throws SQLException {
        String sql = "select stu_no,password from student where  stu_no = ? ";
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
        pst.setString(1, id);
        ResultSet res = pst.executeQuery();
        Map<String, String> tem = new HashMap<String, String>();
        while (res.next()) {
            tem.put(res.getString("stu_no"), res.getString("password"));
        }
        if (tem.isEmpty()) {
            return null;
        } else if (tem.get(id).equals(password)) {
            return tem;
        } else {
            return null;
        }
    }

    public Map<String, String> setTeacherSession(String id, String password, String userType)
            throws SQLException {
        String sql = "select te_no,password from teacher where te_no = ? ";
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
        pst.setString(1, id);
        ResultSet res = pst.executeQuery();
        Map<String, String> tem = new HashMap<String, String>();
        while (res.next()) {
            tem.put(res.getString("te_no"), res.getString("password"));
        }
        if (tem.isEmpty()) {
            return null;
        } else if (tem.get(id).equals(password)) {
            return tem;
        } else {
            return null;
        }
    }

    public Map<String, String> setAdminSession(String id, String password, String userType)
            throws SQLException {
        String sql = "select id,password from admin where  id = ? ";
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
        pst.setString(1, id);
        ResultSet res = pst.executeQuery();
        Map<String, String> tem = new HashMap<String, String>();
        while (res.next()) {
            tem.put(res.getString("id"), res.getString("password"));
        }
        if (tem.isEmpty()) {
            return null;
        } else if (tem.containsKey(id) && tem.get(id).equals(password)) {

            return tem;
        } else {
            return null;
        }
    }
}
