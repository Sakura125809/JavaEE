/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:27:00 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:27:00 
 */
package edu.hit.msc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBhelper {

    public static final String URL = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=UTF-8";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";// 输入密码

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASS);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
