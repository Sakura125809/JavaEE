<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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

    <link href="style/main_teacher.css" type="text/css" rel="stylesheet">

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
                <a href="#" title="没有访问权限">教师管理</a>
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
                <li><a href="teacher_grade_add.jsp">录入成绩</a></li>
                <li><a href="teacher_grade_query.jsp">查看成绩</a></li>
                <li><a href="teacher_grade_update.jsp">修改成绩</a></li>
            </ul>
        </div>
        <div class="center">
            <h3>成绩修改</h3>
            <form action="grade_update.do" method="post">
                <div>
                    <label>&nbsp;学&nbsp;&nbsp;号：&nbsp;</label>
                    <input type="text" name="stu_no" placeholder="输入学号">
                </div>
                <div>
                    <label>课程编号：</label>
                    <input type="text" name="sub_no" placeholder="输入课程编号">
                </div>
                <div>
                    <label>平时成绩：</label>
                    <input type="text" name="usual_grade" placeholder="输入平时成绩">
                </div>
                <div>
                    <label>试卷成绩：</label>
                    <input type="text" name="paper_grade" placeholder="输入试卷成绩">
                </div>
                <div>
                    <label class="abc">教师评语：</label>
                    <textarea style="vertical-align: top;" rows="4" cols="25" name="comment"></textarea>
                </div>
                <div>
                    <button type="submit">确认修改</button>
                </div>
            </form>
            <p id="status" style="display:none;">${status}</p>
            <script type="text/javascript">
                var status = document.getElementById('status').innerText;
                if (status == "successful") {
                    alert(status);
                }
                else if (status == "failed") {
                    alert(status);
                }
            </script>
        </div>
    </div>
    <div class="footer"><div class="footer">版权所有&copy;陈鹏至-刘泽群-薛景耀&amp;哈尔滨工业大学</div></div>
</div>
</body>
</html>
