/*
 * @Author: Sakura 
 * @Date: 2022-08-02 13:39:32 
 * @Last Modified by:   Sakura 
 * @Last Modified time: 2022-08-02 13:39:32 
 */
package cn.edu.hit.dao.impl;

import cn.edu.hit.dao.StudentDao;
import cn.edu.hit.entity.Student;
import cn.edu.hit.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentDaoImpl implements StudentDao {
    @Override
    public void add(Student s) throws SQLException, ClassNotFoundException {
        String sid = s.getSid();
        String sname = s.getSname();
        int age = s.getAge();
        String birthday = s.getBirthday();
        String sql = "insert into student values('" + sid + "', '" + sname + "', " + age + ", '" + birthday + "')";
        DBUtil db = new DBUtil();
        db.executeUpdate(sql);
    }

    @Override
    public void modify(Student s) throws SQLException, ClassNotFoundException {
        String sid = s.getSid();
        String sname = s.getSname();
        int age = s.getAge();
        String birthday = s.getBirthday();
        String sql = "update student set sname = '" + sname + "', age = " + age + ", birthday = '" + birthday + "'where sid = '" + sid + "'";
        DBUtil db = new DBUtil();
        db.executeUpdate(sql);
    }

    @Override
    public void remove(String sid) throws SQLException, ClassNotFoundException {
        String sql = "delete from student where sid = '" + sid + "'";
        DBUtil db = new DBUtil();
        db.executeUpdate(sql);
    }

    @Override
    public List<Student> getAll(String sql) throws SQLException, ClassNotFoundException {
        DBUtil db = new DBUtil();
        ResultSet rs = db.executeQuery(sql);
        List<Student> list = new ArrayList<>();
        String sid;
        String sname;
        int age;
        String birthday;
        while(rs.next()) {
            sid = rs.getString("sid");
            sname = rs.getString("sname");
            age = rs.getInt("age");
            birthday = rs.getString("birthday");
            list.add(new Student(sid, sname, age, birthday));
        }
        return list;
    }

    @Override
    public Student getBySid(String sid) throws SQLException, ClassNotFoundException {
        String sql = "select * from student where sid = '" + sid + "'";
        DBUtil db = new DBUtil();
        ResultSet rs = db.executeQuery(sql);
        if(rs.next()) {
            String sname = rs.getString("sname");
            int age = rs.getInt("age");
            String birthday = rs.getString("birthday");
            return new Student(sid, sname, age, birthday);
        }
        return null;
    }
}
