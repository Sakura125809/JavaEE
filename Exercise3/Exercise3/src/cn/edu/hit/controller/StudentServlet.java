/*
 * @Author: Sakura 
 * @Date: 2022-08-02 13:50:32 
 * @Last Modified by:   Sakura 
 * @Last Modified time: 2022-08-02 13:50:32 
 */
package cn.edu.hit.controller;

import cn.edu.hit.dao.StudentDao;
import cn.edu.hit.dao.impl.StudentDaoImpl;
import cn.edu.hit.entity.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "StudentServlet", value = "/StudentServlet")
public class StudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String from = request.getParameter("from");
        StudentDao sDao = new StudentDaoImpl();
        if(from.equals("add")) {
            String sid = request.getParameter("sid");
            String sname = request.getParameter("sname");
            int age = Integer.parseInt(request.getParameter("age"));
            String birthday = request.getParameter("birthday");
            try {
                sDao.add(new Student(sid, sname, age, birthday));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if(from.equals("modify")) {
            String sid = request.getParameter("sid");
            String sname = request.getParameter("sname");
            int age = Integer.parseInt(request.getParameter("age"));
            String birthday = request.getParameter("birthday");
            try {
                sDao.modify(new Student(sid, sname, age, birthday));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if(from.equals("remove")) {
            String sid = request.getParameter("sid");
            try {
                sDao.remove(sid);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        response.sendRedirect("index.jsp");
    }
}
