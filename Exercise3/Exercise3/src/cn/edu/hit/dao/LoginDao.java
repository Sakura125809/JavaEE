/*
 * @Author: Sakura 
 * @Date: 2022-08-02 13:50:46 
 * @Last Modified by:   Sakura 
 * @Last Modified time: 2022-08-02 13:50:46 
 */
package cn.edu.hit.dao;

import java.sql.SQLException;

public interface LoginDao {
    boolean login(String uid, String pwd) throws SQLException, ClassNotFoundException;
}
