/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:27:48 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:27:48 
 */
package edu.hit.msc.servlet;

import edu.hit.msc.service.GradeService;
import edu.hit.msc.dao.GradeDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GradeServlet extends HttpServlet {

    private GradeService gradeService;

    public GradeServlet() {
        gradeService = new GradeService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().indexOf("/grade_add.do") != -1) {
            add(req, resp);
        }
        if (req.getServletPath().indexOf("/grade_update.do") != -1) {
            update(req, resp);
        }
        if (req.getServletPath().indexOf("/grade_get.do") != -1) {
            try {
                get(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (req.getServletPath().indexOf("/grade_student_get.do") != -1) {
            try {
                studentget(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (req.getServletPath().indexOf("/grade_teacher_get.do") != -1) {
            try {
                teacherget(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private void get(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        String stu_no = req.getParameter("stu_no");
        String sub_no = req.getParameter("sub_no");
        if (sub_no.equals(""))
            sub_no = null;
        if (stu_no.equals(""))
            stu_no = null;
        List<Map<String, Object>> gradeList = new GradeDao().findGradeByStuNoOrSub(stu_no, sub_no);
        req.setAttribute("gradeList", gradeList);
        if (gradeList != null) {
            req.setAttribute("number", gradeList.size());
        }
        req.getRequestDispatcher("admin_grade_query.jsp").forward(req, resp);
    }

    private void studentget(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        String stu_no = (String) req.getSession().getAttribute("id");
        String sub_no = req.getParameter("sub_no");
        if (sub_no.equals(""))
            sub_no = null;
        if (stu_no.equals(""))
            stu_no = null;
        List<Map<String, Object>> gradeList = new GradeDao().findGradeByStuNoOrSub(stu_no, sub_no);
        req.setAttribute("gradeList", gradeList);
        req.setAttribute("number", gradeList.size());
        req.getRequestDispatcher("student_grade_query.jsp").forward(req, resp);
    }

    private void teacherget(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        String stu_no = req.getParameter("stu_no");
        String sub_no = req.getParameter("sub_no");
        if (sub_no.equals(""))
            sub_no = null;
        if (stu_no.equals(""))
            stu_no = null;
        List<Map<String, Object>> gradeList = new GradeDao().findGradeByStuNoOrSub(stu_no, sub_no);
        if (gradeList != null) {
            req.setAttribute("gradeList", gradeList);
            req.setAttribute("number", gradeList.size());
        }
        req.getRequestDispatcher("teacher_grade_query.jsp").forward(req, resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String stu_no = req.getParameter("stu_no");
        String sub_no = req.getParameter("sub_no");
        Float usual_grade = Float.parseFloat(req.getParameter("usual_grade"));
        Float paper_grade = Float.parseFloat(req.getParameter("paper_grade"));
        String comment = req.getParameter("comment");
        Map<String, Object> grade = new HashMap<String, Object>();
        grade.put("stu_no", stu_no);
        grade.put("sub_no", sub_no);
        grade.put("usual_grade", usual_grade);
        grade.put("paper_grade", paper_grade);
        grade.put("comment", comment);
        boolean result = false;
        try {
            result = gradeService.inputUpdateGrade(grade, sub_no);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result) {
            req.setAttribute("status", "successful");
            req.getRequestDispatcher("teacher_grade_update.jsp").forward(req, resp);
        } else {
            req.setAttribute("status", "failed");
            req.getRequestDispatcher("teacher_grade_update.jsp").forward(req, resp);
        }

    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String stu_no = req.getParameter("stu_no");
        String sub_no = req.getParameter("sub_no");
        Float usual_grade = Float.parseFloat(req.getParameter("usual_grade"));
        Float paper_grade = Float.parseFloat(req.getParameter("paper_grade"));
        String comment = req.getParameter("comment");

        Map<String, Object> grade = new HashMap<String, Object>();
        grade.put("stu_no", stu_no);
        grade.put("sub_no", sub_no);
        grade.put("usual_grade", usual_grade);
        grade.put("paper_grade", paper_grade);
        grade.put("comment", comment);
        boolean result = false;
        try {
            result = gradeService.inputGrade(grade, sub_no);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result) {
            req.setAttribute("status", "successful");
            req.getRequestDispatcher("teacher_grade_add.jsp").forward(req, resp);
        } else {
            req.setAttribute("status", "failed");
            req.getRequestDispatcher("teacher_grade_add.jsp").forward(req, resp);
        }

    }

}
