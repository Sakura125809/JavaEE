/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:28:11 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:28:11 
 */
package edu.hit.msc.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.hit.msc.entity.Student;
import edu.hit.msc.dao.StudentDao;

public class StudentServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().indexOf("/student_queryInfo.do") != -1) {
            queryInfo(req, resp);
        }
        if (req.getServletPath().indexOf("/student_queryInfo_fromTe.do") != -1) {
            TequeryInfo(req, resp);
        } else if (req.getServletPath().indexOf("/student_querySubjectInfo.do") != -1) {
            querySubjectInfo(req, resp);
        } else if (req.getServletPath().indexOf("/student_changePassword.do") != -1) {
            changePassword(req, resp);
        } else if (req.getServletPath().indexOf("/student_addStudent.do") != -1) {
            try {
                addStudent(req, resp);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (req.getServletPath().indexOf("/student_changeStuInfo.do") != -1) {
            try {
                changeStuInfo(req, resp);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (req.getServletPath().indexOf("/student_deleteStu.do") != -1) {
            deleteStudent(req, resp);
        }

    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("1", "2");

        // TODO Auto-generated method stub
        String stu_no = req.getParameter("stu_no");
        StudentDao stuDao = new StudentDao();
        boolean f = false;
        try {
            f = stuDao.deleteStudent(stu_no);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (f) {
            req.setAttribute("status", "successful");
            req.getRequestDispatcher("admin_student_delete.jsp").forward(req, resp);
        } else {
            req.setAttribute("status", "failed");
            req.getRequestDispatcher("admin_student_delete.jsp").forward(req, resp);
        }
    }

    private void changeStuInfo(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException, ParseException {
        req.setCharacterEncoding("utf-8");
        Student stu = new Student();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ymd = req.getParameter("year") + "-" + req.getParameter("month") + "-" + req.getParameter("day");
        java.util.Date date = dateFormat.parse(ymd);
        stu.setStu_no(req.getParameter("stu_no"));
        stu.setStu_name(req.getParameter("stu_name"));
        stu.setClazz(req.getParameter("class"));
        stu.setSex(req.getParameter("sex"));
        stu.setDepartment(req.getParameter("department"));
        stu.setMajor(req.getParameter("major"));
        stu.setClazz(req.getParameter("class"));
        stu.setPassword(req.getParameter("password"));
        stu.setAdmissiontime(date);
        StudentDao stuDao = new StudentDao();
        boolean f = false;
        try {
            f = stuDao.updateStudent(stu);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (f) {
            req.setAttribute("status", "successful");
            req.getRequestDispatcher("admin_student_update.jsp").forward(req, resp);
        } else {
            req.setAttribute("status", "failed");
            req.getRequestDispatcher("admin_student_update.jsp").forward(req, resp);
        }
    }

    private void addStudent(HttpServletRequest req, HttpServletResponse resp)
            throws ParseException, IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ymd = req.getParameter("year") + "-" + req.getParameter("month") + "-" + req.getParameter("day");
        java.util.Date date = dateFormat.parse(ymd);
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        Student stu = new Student();
        stu.setSex(req.getParameter("sex"));
        stu.setStu_no(req.getParameter("stu_no"));
        stu.setStu_name(req.getParameter("stu_name"));
        stu.setClazz(req.getParameter("class"));
        stu.setAdmissiontime(sqldate);
        stu.setDepartment(req.getParameter("department"));
        stu.setMajor(req.getParameter("major"));
        stu.setPassword("123456");
        StudentDao stuDao = new StudentDao();
        boolean f = false;
        try {
            f = stuDao.addStudent(stu);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (f) {
            req.setAttribute("status", "successful");
            req.getRequestDispatcher("admin_student_add.jsp").forward(req, resp);
        } else {
            req.setAttribute("status", "failed");
            req.getRequestDispatcher("admin_student_add.jsp").forward(req, resp);
        }

    }

    private void changePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String stu_no = (String) req.getSession().getAttribute("id");
        String old_password = req.getParameter("old_password");
        String new_password = req.getParameter("new_password");
        String confirm_password = req.getParameter("confirm_password");
        StudentDao stuDao = new StudentDao();
        boolean f = false;
        try {
            f = stuDao.updatePassword(stu_no, old_password, new_password, confirm_password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (f) {
            req.setAttribute("status", "successful");
            req.getRequestDispatcher("student_password.jsp").forward(req, resp);
        } else {
            req.setAttribute("status", "failed");
            req.getRequestDispatcher("student_password.jsp").forward(req, resp);
        }
    }

    private void querySubjectInfo(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("utf-8");
        String stu_no = (String) req.getSession().getAttribute("id");
        StudentDao stuDao = new StudentDao();
        List<Map<String, Object>> subjectList = new LinkedList<Map<String, Object>>();
        try {
            subjectList = stuDao.querysubject(stu_no);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (subjectList.size() > 0) {
            req.setAttribute("subjectList", subjectList);
            req.setAttribute("number", subjectList.size());
            req.getRequestDispatcher("student_subject_query.jsp").forward(req, resp);
        } else {
            req.setAttribute("status", "failed");
            req.getRequestDispatcher("student_subject_query.jsp").forward(req, resp);
        }

    }

    private void queryInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("utf-8");
        String stu_no = req.getParameter("stu_no");
        StudentDao stuDao = new StudentDao();
        List<Student> studentList = new LinkedList<Student>();
        boolean f = false;
        try {
            studentList = stuDao.queryOneInfo(stu_no, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("stuList", studentList);
        req.setAttribute("number", studentList.size());
        req.getRequestDispatcher("admin_student_query.jsp").forward(req, resp);
    }

    private void TequeryInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("utf-8");
        String stu_no = req.getParameter("stu_no");
        StudentDao stuDao = new StudentDao();
        List<Student> studentList = new LinkedList<Student>();
        boolean f = false;
        try {
            studentList = stuDao.queryOneInfo(stu_no, (String) req.getSession().getAttribute("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("stuList", studentList);
        req.setAttribute("number", studentList.size());
        req.getRequestDispatcher("teacher_student_query.jsp").forward(req, resp);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
