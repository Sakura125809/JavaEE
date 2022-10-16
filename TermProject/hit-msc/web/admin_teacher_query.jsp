<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page language="java" import="edu.hit.msc.entity.Teacher" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>学生成绩管理系统</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link href="style/main_admin_query.css" type="text/css" rel="stylesheet">

</head>

<body>
<div class="container">
    <div class="header">
        <img alt="" src="image/xiaohui2.png" width="100px" height="100px">
        <form action="destroySession.do">
            <button type="submit">退出系统</button>
        </form>
    </div>
    <div class="nav nav-cls1">
        <ul>
            <li>
                <a class="index" href="admin_index.jsp">首页</a>
            </li>
            <li>
                <a href="admin_student.jsp">学生管理</a>
            </li>
            <li>
                <a href="admin_teacher.jsp">教师管理</a>
            </li>
            <li>
                <a href="admin_subject.jsp">课程管理</a>
            </li>
            <li>
                <a href="admin_grade.jsp">成绩管理</a>
            </li>
            <li>
                <a href="admin_password.jsp">修改密码</a>
            </li>
        </ul>
    </div>
    <div class="main">
        <div class="nav nav-cls2 left">
            <h3></h3>
            <ul>
                <li><a href="admin_teacher_query.jsp">查看教师信息</a></li>
                <li><a href="admin_teacher_add.jsp">添加教师信息</a></li>
                <li><a href="admin_teacher_update.jsp">修改教师信息</a></li>
                <li><a href="admin_teacher_delete.jsp">删除教师信息</a></li>
            </ul>
        </div>
        <div class="center">
            <h3>查询教师信息：</h3>
            <form action="teacher_query.do">
                <input type="text" name="te_no" placeholder="输入教师工号">
                <button type="submit">查询</button>
            </form>
            <hr>
            <table border="1">
                <thead>
                <tr>
                    <th>工号</th>
                    <th>姓名</th>
                    <th>所属院系</th>
                    <th>工资</th>
                    <th>入职时间</th>
                    <th>邮箱</th>
                    <th>电话</th>
                    <th>职位</th>
                    <th>密码</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${teacherList }" var="te">
                    <tr>
                        <td>${te.te_no }</td>
                        <td>${te.te_name }</td>
                        <td>${te.department }</td>
                        <td>${te.wage }</td>
                        <td>${te.entry_time }</td>
                        <td>${te.email }</td>
                        <td>${te.phonenumber }</td>
                        <td>${te.job_title }</td>
                        <td>${te.password }</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <p>共找到${number}条记录！</p>
        </div>
    </div>
    <div class="footer"><div class="footer">版权所有&copy;陈鹏至-刘泽群-薛景耀&amp;哈尔滨工业大学</div></div>
</div>
</body>
</html>
