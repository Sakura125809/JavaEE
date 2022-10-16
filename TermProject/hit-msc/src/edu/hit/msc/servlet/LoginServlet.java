/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:28:01 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:28:01 
 */
package edu.hit.msc.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import edu.hit.msc.dao.LoginDao;
import edu.hit.msc.tools.VCodeGenerator;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String method = req.getParameter("method");

        if ("GetVCode".equalsIgnoreCase(method)) {
            getVCode(req, resp);
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String method = req.getParameter("method");


        if ("Login".equals(method)) { //验证登录
            login(req, resp);
        }
    }


    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String msg = "";
        HttpSession hs = req.getSession();
        String userType = req.getParameter("login");
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        LoginDao loginDao = new LoginDao();

        String vcode = req.getParameter("vcode");
        String sVcode = (String) req.getSession().getAttribute("vcode");
        if (!sVcode.equalsIgnoreCase(vcode)) {
            msg = "vcodeError";
        } else {
            if (userType.equals("admin")) {
                msg = "admin";

                Map<String, String> tem = null;
                try {
                    tem = loginDao.setAdminSession(id, password, userType);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (tem == null) {
                    msg = "loginError";
                } else {
                    hs.setAttribute("userType", userType);
                    hs.setAttribute("id", id);
                    hs.setAttribute("password", password);
                }
            } else if (userType.equals("teacher")) {
                msg = "teacher";
                Map<String, String> tem = null;
                try {
                    tem = loginDao.setTeacherSession(id, password, userType);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (tem == null) {
                    msg = "loginError";
                } else {
                    hs.setAttribute("userType", userType);
                    hs.setAttribute("id", id);
                    hs.setAttribute("password", password);
                }
            } else {
                Map<String, String> tem = null;
                msg = "student";
                try {
                    tem = loginDao.setStudentSession(id, password, userType);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (tem == null) {
                    msg = "loginError";
                } else {
                    hs.setAttribute("userType", userType);
                    hs.setAttribute("id", id);
                    hs.setAttribute("password", password);
                }
            }
            resp.getWriter().write(msg);
        }
    }

    private void getVCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VCodeGenerator vcGenerator = new VCodeGenerator();

        String vcode = vcGenerator.generatorVCode();

        request.getSession().setAttribute("vcode", vcode);

        BufferedImage vImg = vcGenerator.generatorRotateVCodeImage(vcode, true);

        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());

        encoder.encode(vImg);
    }
}




