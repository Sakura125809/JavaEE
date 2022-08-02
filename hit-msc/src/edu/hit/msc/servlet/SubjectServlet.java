/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:28:19 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:28:19 
 */
package edu.hit.msc.servlet;

import edu.hit.msc.entity.Subject;
import edu.hit.msc.dao.SubjectDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SubjectServlet")
public class SubjectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getServletPath().indexOf("/subject_add.do") != -1) {
            try {
                add(req, resp);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (req.getServletPath().indexOf("/subject_update.do") != -1) {
            try {
                update(req, resp);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (req.getServletPath().indexOf("/subject_delete.do") != -1) {
            try {
                delete(req, resp);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (req.getServletPath().indexOf("/subject_student_get.do") != -1) {
            try {
                studentget(req, resp);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (req.getServletPath().indexOf("/subject_get.do") != -1) {
            try {
                get(req, resp);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (req.getServletPath().indexOf("/subject_admin_get.do") != -1) {
            try {
                adminGet(req, resp);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void studentget(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        // TODO Auto-generated method stub
        String sub_no = request.getParameter("sub_no");
        List<Subject> teacherList = new SubjectDao().findSubject(sub_no);
        request.setAttribute("subjectList", teacherList);
        request.getRequestDispatcher("student_subject_query.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    private void get(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String te_no = request.getParameter("te_no");
        List<Subject> teacherList = new SubjectDao().findSubjectByTeaher((String) request.getSession().getAttribute("id"));
        request.setAttribute("subjectList", teacherList);
        request.setAttribute("number", teacherList.size());
        request.getRequestDispatcher("teacher_subject_query.jsp").forward(request, response);

    }

    private void adminGet(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String sub_no = request.getParameter("sub_no");
        List<Subject> teacherList = new SubjectDao().findSubject(sub_no);
        request.setAttribute("subjectList", teacherList);
        request.getRequestDispatcher("admin_subject_query.jsp").forward(request, response);

    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        Subject subject = new Subject(request.getParameter("sub_no"),
                request.getParameter("sub_name"),
                request.getParameter("open_department"),
                Double.parseDouble(request.getParameter("paper_grade_per")) / 100
        );
        boolean n = false;
        try {
            n = new SubjectDao().addSubject(subject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (n) {
            request.setAttribute("status", "successful");
            request.getRequestDispatcher("admin_subject_add.jsp").forward(request, response);
        } else {
            request.setAttribute("status", "failed");
            request.getRequestDispatcher("admin_subject_add.jsp").forward(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        Subject subject = new Subject(request.getParameter("sub_no"),
                request.getParameter("sub_name"),
                request.getParameter("open_department"),
                (Double.parseDouble(request.getParameter("paper_grade_per")) / 100)
        );
        boolean n = new SubjectDao().updateSubject(subject);
        if (n) {
            request.setAttribute("status", "successful");
            request.getRequestDispatcher("admin_subject_update.jsp").forward(request, response);
        } else {
            request.setAttribute("status", "failed");
            request.getRequestDispatcher("admin_subject_update.jsp").forward(request, response);
        }

    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, SQLException {
        String sub_no = request.getParameter("sub_no");

        boolean n = new SubjectDao().deleteSubject(sub_no);
        if (n) {
            request.setAttribute("status", "successful");
            request.getRequestDispatcher("admin_subject_delete.jsp").forward(request, response);
        } else {
            request.setAttribute("status", "failed");
            request.getRequestDispatcher("admin_subject_delete.jsp").forward(request, response);
        }
    }
}
