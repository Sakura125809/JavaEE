/*
 * @Author: Sakura 
 * @Date: 2022-08-02 13:51:01 
 * @Last Modified by:   Sakura 
 * @Last Modified time: 2022-08-02 13:51:01 
 */
package cn.edu.hit.util;

import java.sql.*;

public class DBUtil {
    private Connection con;
    private Statement stmt;
    private static final String URL = "jdbc:mysql://localhost:3306/hit";
    private static final String USER = "root";
    private static final String PASSWORD = "";// 输入密码
    public DBUtil() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, USER, PASSWORD);
        stmt = con.createStatement();
    }
    public void executeUpdate(String sql) throws SQLException {//增删改
        stmt.executeUpdate(sql);
    }
    public ResultSet executeQuery(String sql) throws SQLException {//查
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
    public void close() throws SQLException {
        if(!stmt.isClosed()) stmt.close();
        if(!con.isClosed()) con.close();
    }
}
