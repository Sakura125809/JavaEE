/*
 * @Author: Sakura 
 * @Date: 2022-08-02 13:50:27 
 * @Last Modified by:   Sakura 
 * @Last Modified time: 2022-08-02 13:50:27 
 */
package cn.edu.hit.controller;

import cn.edu.hit.dao.LoginDao;
import cn.edu.hit.dao.impl.LoginDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String uid = request.getParameter("uid");
        String pwd = request.getParameter("pwd");
        LoginDao lDao = new LoginDaoImpl();
        PrintWriter out = response.getWriter();
        try {
            if(lDao.login(uid, pwd)) {
                HttpSession session = request.getSession();
                session.setAttribute("uid", uid);
                session.setAttribute("pwd", pwd);
                String remeberMe = request.getParameter("remember");
                if(remeberMe != null && remeberMe.equals("1")) {
                    Cookie cookieUid = new Cookie("uid", uid);
                    Cookie cookiePwd = new Cookie("pwd", pwd);
                    cookieUid.setMaxAge(7 * 24 * 3600);
                    cookiePwd.setMaxAge(7 * 24 * 3600);
                    response.addCookie(cookieUid);
                    response.addCookie(cookiePwd);
                }
                response.sendRedirect("index.jsp");
            } else {
                out.print("用户名或密码错误！");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
