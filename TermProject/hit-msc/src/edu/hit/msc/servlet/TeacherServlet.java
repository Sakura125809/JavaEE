/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:28:26 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:28:26 
 */
package edu.hit.msc.servlet;

import edu.hit.msc.entity.Teacher;
import edu.hit.msc.dao.TeacherDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "TeacherServlet")
public class TeacherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getServletPath().indexOf("/teacher_add.do") != -1) {
            try {
                add(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (request.getServletPath().indexOf("/teacher_update.do") != -1) {
            try {
                update(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (request.getServletPath().indexOf("/teacher_query.do") != -1) {
            try {
                get(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (request.getServletPath().indexOf("/teacher_delete.do") != -1) {
            delete(request, response);
        }
        if (request.getServletPath().indexOf("/teacher_changePassword.do") != -1) {
            try {
                changePassword(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    private void get(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String te_no = request.getParameter("te_no");
        if (te_no.equals(""))
            te_no = null;
        List<Teacher> teacherList = new TeacherDao().findTeacherByTeNoOrSubNo(te_no, null);
        request.setAttribute("teacherList", teacherList);
        request.getRequestDispatcher("admin_teacher_query.jsp").forward(request, response);


    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ymd = request.getParameter("year") + "-" + request.getParameter("month") + "-" + request.getParameter("day");
        java.util.Date date = dateFormat.parse(ymd);
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        Teacher teacher = new Teacher(request.getParameter("te_no"),
                request.getParameter("te_name"),
                "123456",
                request.getParameter("department"),
                Integer.parseInt(request.getParameter("wage")),
                sqldate,
                request.getParameter("email"),
                request.getParameter("phonenumber"),
                request.getParameter("job_title"));
        int n = new TeacherDao().addTeacher(teacher);
        if (n == 1) {
            request.setAttribute("status", "successful");
            request.getRequestDispatcher("admin_teacher_add.jsp").forward(request, response);
        } else {
            request.setAttribute("status", "failed");
            request.getRequestDispatcher("admin_teacher_add.jsp").forward(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("utf-8");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ymd = request.getParameter("year") + "-" + request.getParameter("month") + "-" + request.getParameter("day");
        java.util.Date date = dateFormat.parse(ymd);
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        Teacher teacher = new Teacher(request.getParameter("te_no"),
                request.getParameter("te_name"),
                request.getParameter("password"),
                request.getParameter("department"),
                Integer.parseInt(request.getParameter("wage")),
                sqldate,
                request.getParameter("email"),
                request.getParameter("phonenumber"),
                request.getParameter("job_title"));
        int n = new TeacherDao().updateTeacher(teacher);
        if (n == 1) {
            request.setAttribute("status", "successful");
            request.getRequestDispatcher("admin_teacher_update.jsp").forward(request, response);
        } else {
            request.setAttribute("status", "failed");
            request.getRequestDispatcher("admin_teacher_update.jsp").forward(request, response);
        }

    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String te_no = request.getParameter("te_no");
        int n = 0;
        try {
            n = new TeacherDao().deleteTeacher(te_no);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (n == 1) {
            request.setAttribute("status", "successful");
            request.getRequestDispatcher("admin_teacher_delete.jsp").forward(request, response);
        } else {
            request.setAttribute("status", "failed");
            request.getRequestDispatcher("admin_teacher_delete.jsp").forward(request, response);
        }
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String te_no = (String) request.getSession().getAttribute("id");
        String oldPassword = request.getParameter("old_password");
        String newPassword = request.getParameter("new_password");
        String confirmPassword = request.getParameter("confirm_password");
        boolean n = new TeacherDao().updatePassword(te_no, oldPassword, newPassword, confirmPassword);
        if (n) {
            request.setAttribute("status", "successful");
            request.getRequestDispatcher("teacher_password.jsp").forward(request, response);
        } else {
            request.setAttribute("status", "failed");
            request.getRequestDispatcher("teacher_password.jsp").forward(request, response);
        }
    }
}
