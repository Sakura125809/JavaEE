<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page language="java" import="edu.hit.msc.entity.Student" %>
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

    <link href="style/main_teacher_query.css" type="text/css" rel="stylesheet">

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
                <a class="index" href="teacher_index.jsp">首页</a>
            </li>
            <li>
                <a href="teacher_student.jsp">学生管理</a>
            </li>
            <li>
                <a href="javascript:void(0);" title="没有访问权限">教师管理</a>
            </li>
            <li>
                <a href="teacher_subject.jsp">课程管理</a>
            </li>
            <li>
                <a href="teacher_grade.jsp">成绩管理</a>
            </li>
            <li>
                <a href="teacher_password.jsp">修改密码</a>
            </li>
        </ul>
    </div>
    <div class="main">
        <div class="nav nav-cls2 left">
            <h3></h3>
            <ul>
                <li><a href="teacher_student_query.jsp">查看学生信息</a></li>
            </ul>
        </div>
        <div class="center">
            <h3>查询学生信息：</h3>
            <form action="student_queryInfo_fromTe.do">
                <input type="text" name="stu_no" placeholder="输入学号">
                <button type="submit">查询</button>
            </form>
            <hr>
            <table border="1">
                <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>班级</th>
                    <th>院系</th>
                    <th>专业</th>
                    <th>入学时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${stuList }" var="stu">
                    <tr>
                        <td>${stu.stu_no }</td>
                        <td>${stu.stu_name }</td>
                        <td>${stu.sex }</td>
                        <td>${stu.clazz }</td>
                        <td>${stu.department }</td>
                        <td>${stu.major }</td>
                        <td>${stu.admissiontime }</td>
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
