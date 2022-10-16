/*
 * @Author: Sakura 
 * @Date: 2022-08-02 13:39:27 
 * @Last Modified by:   Sakura 
 * @Last Modified time: 2022-08-02 13:39:27 
 */
package cn.edu.hit.dao.impl;

import cn.edu.hit.dao.LoginDao;
import cn.edu.hit.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao {
    @Override
    public boolean login(String uid, String pwd) throws SQLException, ClassNotFoundException {
        String sql = "select count(*) from sysuser where uid = '" + uid + "' and pwd = '" + pwd + "'";
        DBUtil db = new DBUtil();
        ResultSet rs = db.executeQuery(sql);
        int count = 0;
        rs.next();
        count = rs.getInt(1);
        return count == 1;
    }
}
