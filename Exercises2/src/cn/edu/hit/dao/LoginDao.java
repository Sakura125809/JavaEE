/*
 * @Author: Sakura 
 * @Date: 2022-08-02 13:39:13 
 * @Last Modified by:   Sakura 
 * @Last Modified time: 2022-08-02 13:39:13 
 */
package cn.edu.hit.dao;

import java.sql.SQLException;

public interface LoginDao {
    boolean login(String uid, String pwd) throws SQLException, ClassNotFoundException;
}
