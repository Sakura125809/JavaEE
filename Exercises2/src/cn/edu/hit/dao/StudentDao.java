/*
 * @Author: Sakura 
 * @Date: 2022-08-02 13:39:18 
 * @Last Modified by:   Sakura 
 * @Last Modified time: 2022-08-02 13:39:18 
 */
package cn.edu.hit.dao;
import cn.edu.hit.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    void add(Student s) throws SQLException, ClassNotFoundException;
    void modify(Student s) throws SQLException, ClassNotFoundException;
    void remove(String sid) throws SQLException, ClassNotFoundException;
    List<Student> getAll(String sql) throws SQLException, ClassNotFoundException;
    Student getBySid(String sid) throws SQLException, ClassNotFoundException;
}
