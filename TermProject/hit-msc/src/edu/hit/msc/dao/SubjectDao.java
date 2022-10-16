/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:26:46 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:26:46 
 */
package edu.hit.msc.dao;

import edu.hit.msc.entity.Subject;
import edu.hit.msc.db.DBhelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SubjectDao {
    private Connection conn;

    public SubjectDao() {
        try {
            conn = DBhelper.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Subject> findSubject(String sub_no) throws SQLException {
        String sql = null;
        PreparedStatement pst = null;
        if (sub_no == null || "".equals(sub_no)) {
            sql = "select * from subject";
            pst = conn.prepareStatement(sql);
        } else {
            sql = "select * from subject where sub_no=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, sub_no);
        }
        ResultSet res = pst.executeQuery();
        List<Subject> subjectList = new LinkedList<>();
        while (res.next()) {
            subjectList.add(new Subject(res.getString("sub_no"), res.getString("sub_name"),
                    res.getString("open_department"), Double.parseDouble(res.getString("paper_grade_per")) * 100));
        }
        return subjectList;
    }

    public boolean addSubject(Subject subject) throws SQLException {
        String sql = "insert into subject values(?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, subject.getSub_no());
        pst.setString(2, subject.getSub_name());
        pst.setString(3, subject.getOpen_department());
        pst.setDouble(4, subject.getPaper_grade_per());
        int n = pst.executeUpdate();
        return n != 0;
    }

    public boolean updateSubject(Subject subject) throws SQLException {
        String sql = "update subject set sub_name=?,open_department=?,paper_grade_per=? where sub_no=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, subject.getSub_name());
        pst.setString(2, subject.getOpen_department());
        pst.setDouble(3, subject.getPaper_grade_per());
        pst.setString(4, subject.getSub_no());
        int n = pst.executeUpdate();
        return n != 0;
    }

    public boolean deleteSubject(String sub_no) throws SQLException {
        String sql = "delete from subject where sub_no=? ";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, sub_no);
        int n = pst.executeUpdate();
        return n != 0;

    }

    public List<Subject> findSubjectByTeaher(String te_no) throws SQLException {
        String sql = "select distinct sts.te_no,sub.sub_no,sub.sub_name,sub.open_department,sub.paper_grade_per"
                + " from subject sub,stu_te_sub sts where sts.te_no=? and sts.sub_no=sub.sub_no ";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, te_no);
        ResultSet res = pst.executeQuery();
        List<Subject> subjectList = new LinkedList<>();
        while (res.next()) {
            subjectList.add(new Subject(res.getString("sub_no"), res.getString("sub_name"),
                    res.getString("open_department"), Double.parseDouble(res.getString("paper_grade_per")) * 100));
        }
        return subjectList;
    }
}
