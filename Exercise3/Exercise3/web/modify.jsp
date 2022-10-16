<%@ page import="cn.edu.hit.dao.StudentDao" %>
<%@ page import="cn.edu.hit.dao.impl.StudentDaoImpl" %>
<%@ page import="cn.edu.hit.entity.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String uid = (String)session.getAttribute("uid");
    if(uid == null) {
        out.print("非法用户，请<a href='login.jsp'>登录</a>！");
        return;
    }
    String sid = request.getParameter("sid");
    StudentDao sDao = new StudentDaoImpl();
    Student s = sDao.getBySid(sid);
    String sname = s.getSname();
    int age = s.getAge();
    String birthday = s.getBirthday();
%>
<form method="post" action="StudentServlet">
    <input type="hidden" name="from" value="modify">
    <table border = "1">
        <tr>
            <td>学号</td>
            <td><input type="text" name="sid" value="<%=sid%>" readonly></td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><input type="text" name="sname" value="<%=sname%>"></td>
        </tr>
        <tr>
            <td>年龄</td>
            <td><input type="text" name="age" value="<%=age%>"></td>
        </tr>
        <tr>
            <td>生日</td>
            <td><input type="text" name="birthday" value="<%=birthday%>"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit"></td>
        </tr>
    </table>
</form>
</body>
</html>
