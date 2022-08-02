/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:26:27 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:26:27 
 */
package edu.hit.msc.dao;

import java.util.*;

import java.sql.*;


import edu.hit.msc.db.DBhelper;

public class GradeDao {

    private Connection conn;

    public GradeDao() {
        try {
            conn = DBhelper.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int addGrade(Map<String, Object> grade) throws Exception {

        String sql = "insert into Grade values(?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, (String) grade.get("stu_no"));
        pst.setString(2, (String) grade.get("sub_no"));
        pst.setObject(3, grade.get("usual_grade"), Types.DOUBLE);
        pst.setObject(4, grade.get("paper_grade"), Types.DOUBLE);
        pst.setObject(5, grade.get("final_grade"), Types.DOUBLE);
        pst.setString(6, (String) grade.get("comment"));
        int n = pst.executeUpdate();
        return n;
    }

    public int updateGrade(Map<String, Object> grade) throws Exception {
        String sql = "update grade set usual_grade=?,paper_grade=?,final_grade=?,comment=? where stu_no = ? and sub_no = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setObject(1, grade.get("usual_grade"), Types.DOUBLE);
        pst.setObject(2, grade.get("paper_grade"), Types.DOUBLE);
        pst.setObject(3, grade.get("final_grade"), Types.DOUBLE);
        pst.setString(4, (String) grade.get("comment"));
        pst.setString(5, (String) grade.get("stu_no"));
        pst.setString(6, (String) grade.get("sub_no"));
        int n = pst.executeUpdate();
        return n;
    }

    public List<Map<String, Object>> findGradeByStuNoOrSub(String stu_no, String sub_no)
            throws SQLException {
        String sql = null;
        if (("".equals(stu_no) && "".equals(sub_no)) || (stu_no == null && sub_no == null)) {
            return null;
        } else {
            sql = "select s.stu_no,s.stu_name,sub.sub_no,sub.sub_name,g.final_grade,g.usual_grade,g.paper_grade,g.comment" +
                    " from student s,subject sub,grade g where s.stu_no=g.stu_no and " +
                    "sub.sub_no=g.sub_no";
        }
        StringBuilder sb = new StringBuilder(sql);
        if (stu_no != null) {
            sb.append(" and s.stu_no=? ");
        }
        if (sub_no != null) {
            sb.append(" and sub.sub_no=? ");
        }
        List<Map<String, Object>> gradeList = new LinkedList<Map<String, Object>>();
        PreparedStatement pst = conn.prepareStatement(sb.toString());
        if (stu_no != null) {
            pst.setString(1, stu_no);
            if (sub_no != null) {
                pst.setString(2, sub_no);
            }
        } else {
            pst.setString(1, sub_no);
        }
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            Map<String, Object> tem = new HashMap<String, Object>();
            tem.put("stu_no", res.getString("stu_no"));
            tem.put("stu_name", res.getString("stu_name"));
            tem.put("sub_no", res.getString("sub_no"));
            tem.put("sub_name", res.getString("sub_name"));
            tem.put("usual_grade", res.getDouble("usual_grade"));
            tem.put("paper_grade", res.getDouble("paper_grade"));
            tem.put("final_grade", res.getDouble("final_grade"));
            tem.put("comment", res.getString("comment"));
            gradeList.add(tem);
        }
        return gradeList;
    }

}






