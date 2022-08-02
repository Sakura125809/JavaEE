/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:27:35 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:27:35 
 */
package edu.hit.msc.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServlet;

import edu.hit.msc.dao.AdminDao;

public class AdminServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getServletPath().indexOf("admin_changePassword.do") != -1) {
            changePassword(req, resp);
        }
    }

    private void changePassword(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String old_password = req.getParameter("old_password");
        String new_password = req.getParameter("new_password");
        String confirm_password = req.getParameter("confirm_password");
        String id = (String) req.getSession().getAttribute("id");
        AdminDao adminDao = new AdminDao();
        boolean f = false;
        try {
            f = adminDao.updatePassword(id, old_password, new_password, confirm_password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (f) {
            req.setAttribute("status", "successful");
            req.getRequestDispatcher("admin_password.jsp").forward(req, resp);
        } else {
            req.setAttribute("status", "failed");
            req.getRequestDispatcher("admin_password.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}







