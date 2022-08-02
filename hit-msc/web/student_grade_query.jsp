<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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

    <link href="style/main_student_query.css" type="text/css" rel="stylesheet">

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
                <a class="index" href="student_index.jsp">首页</a>
            </li>
            <li>
                <a href="javascript:void(0);" title="没有访问权限">学生管理</a>
            </li>
            <li>
                <a href="#" title="没有访问权限">教师管理</a>
            </li>
            <li>
                <a href="student_subject.jsp">课程管理</a>
            </li>
            <li>
                <a href="student_grade.jsp">成绩管理</a>
            </li>
            <li>
                <a href="student_password.jsp">修改密码</a>
            </li>
        </ul>
    </div>
    <div class="main">
        <div class="nav nav-cls2 left">
            <h3></h3>
            <ul>
                <li><a href="student_grade_query.jsp">查看成绩信息</a></li>
            </ul>
        </div>
        <div class="center">
            <h3>查询成绩信息：</h3>
            <form action="grade_student_get.do">
                <input type="text" name="sub_no" placeholder="输入课程号">
                <button type="submit">查询</button>
            </form>
            <hr>
            <table border="1">
                <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>课程号</th>
                    <th>课程名</th>
                    <th>平时成绩</th>
                    <th>卷面成绩</th>
                    <th>总成绩</th>
                    <th>评语</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${gradeList }" var="grade">
                    <tr>
                        <td>${grade.get("stu_no")}</td>
                        <td>${grade.get("stu_name") }</td>
                        <td>${grade.get("sub_no")}</td>
                        <td>${grade.get("sub_name")}</td>
                        <td>${grade.get("usual_grade")}</td>
                        <td>${grade.get("paper_grade")}</td>
                        <td>${grade.get("final_grade")}</td>
                        <td>${grade.get("comment")}</td>
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
