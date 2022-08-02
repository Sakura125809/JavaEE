<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>学生成绩管理系统</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link href="style/main_admin.css" type="text/css" rel="stylesheet">

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
            <li><a class="index" href="admin_index.jsp">首页</a></li>
            <li><a href="admin_student.jsp">学生管理</a></li>
            <li><a href="admin_teacher.jsp">教师管理</a></li>
            <li><a href="admin_subject.jsp">课程管理</a></li>
            <li><a href="admin_grade.jsp">成绩管理</a></li>
            <li><a href="admin_password.jsp">修改密码</a></li>
        </ul>
    </div>
    <div class="main">
        <div class="nav nav-cls2 left">
            <h3></h3>
            <ul>
                <li><a href="admin_student_query.jsp">查看学生信息</a></li>
                <li><a href="admin_student_add.jsp">添加学生信息</a></li>
                <li><a href="admin_student_update.jsp">修改学生信息</a></li>
                <li><a href="admin_student_delete.jsp">删除学生信息</a></li>
            </ul>
        </div>
        <div class="center">
            <h3>修改学生信息</h3>
            <form action="student_changeStuInfo.do" method="post">
                <div>
                    <label>学号：</label> <input type="text" name="stu_no"
                                              placeholder="输入学号">
                </div>
                <div>
                    <label>姓名：</label> <input type="text" name="stu_name"
                                              placeholder="输入姓名">
                </div>
                <div>
                    <label>院系：</label> <input type="text" name="department"
                                              placeholder="输入院系名称">
                </div>
                <div>
                    <label>专业：</label> <input type="text" name="major"
                                              placeholder="输入专业名称">
                </div>
                <div>
                    <label>班级：</label> <input type="text" name="class"
                                              placeholder="输入班级">
                </div>
                <div>
                    <label>密码：</label> <input type="text" name="password"
                                              placeholder="输入新密码">
                </div>
                <div>
                    <label>性别：</label> 男<input type="radio" name="sex" value="男"
                                               checked> 女<input type="radio" name="sex" value="女">
                </div>
                <div>
                    <label>入学时间：</label> <input type="number" max="2100" min="2010"
                                                name="year"> <input type="number" max="12" min="1"
                                                                    name="month"> <input type="number" max="30" min="1"
                                                                                         name="day">
                </div>
                <div>
                    <button type="submit">确认修改</button>
                </div>
            </form>
        </div>
    </div>
    <p id="status" style="display:none;">${status}</p>
    <script type="text/javascript">
        var status = document.getElementById('status').innerText;
        if (status == "successful") {
            alert(status);
        } else if (status == "failed") {
            alert(status);
        }
    </script>
    <div class="footer"><div class="footer">版权所有&copy;陈鹏至-刘泽群-薛景耀&amp;哈尔滨工业大学</div></div>
</div>
</body>
</html>
