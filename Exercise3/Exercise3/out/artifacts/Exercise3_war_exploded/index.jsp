<%@ page import="cn.edu.hit.dao.StudentDao" %>
<%@ page import="cn.edu.hit.dao.impl.StudentDaoImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.hit.entity.Student" %>
<%--
  Created by IntelliJ IDEA.
  User: ROG
  Date: 2022/7/18
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>学生管理站</title>
  </head>
  <body>
  <%
    String uid = (String)session.getAttribute("uid");
    if(uid == null) {
      out.print("非法用户，请<a href='login.jsp'>登录</a>！");
      return;
    }
    StudentDao sDao = new StudentDaoImpl();
    List<Student> list = sDao.getAll("select * from student");
  %>
  <a href = "add.html">增加学生</a>
  <table border = "1">
    <tr>
      <td>学号</td><td>姓名</td><td>年龄</td><td>生日</td><td>修改</td><td>删除</td>
    </tr>
    <%
      for(Student s : list) {
    %>
    <tr>
      <td><%=s.getSid()%></td><td><%=s.getSname()%></td><td><%=s.getAge()%></td><td><%=s.getBirthday()%></td><td><a href="modify.jsp?sid=<%=s.getSid()%>">修改</a></td><td><a onclick="return confirm('是否确定删除？')" href="StudentServlet?from=remove&sid=<%=s.getSid()%>">删除</a></td>
    </tr>
    <%
      }
    %>
  </table>
  </body>
</html>
