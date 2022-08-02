<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href=.>
    <title>登录学生成绩管理系统</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="favicon.ico"/>
    <link href="style/main_login.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>


    <script type="text/javascript">
        $(function () {
            //点击图片切换验证码
            $("#vcodeImg").click(function () {
                this.src = "/Login?method=GetVcode&t=" + new Date().getTime();
            });

            //登录
            $("#submitBtn").click(function () {
                //alert("sdfd")
                var data = $("#form").serialize();

                $.ajax({
                    type: "post",
                    url: "/Login?method=Login",
                    data: data,
                    dataType: "text", //返回数据类型
                    success: function (msg) {
                        if ("vcodeError" == msg) {
                            alert("验证码错误!");
                            $("#vcodeImg").click();//切换验证码
                            $("input[name='vcode']").val("");//清空验证码输入框
                        } else if ("loginError" == msg) {
                            alert("用户名或密码错误!");
                            $("#vcodeImg").click();//切换验证码
                            $("input[name='vcode']").val("");//清空验证码输入框
                        } else if ("admin" == msg) {
                            window.location.href = "/admin_index.jsp";
                        } else if ("student" == msg) {
                            window.location.href = "/student_index.jsp";
                        } else if ("teacher" == msg) {
                            window.location.href = "/teacher_index.jsp";
                        }
                    }
                });
            });
        })
    </script>

</head>

<body>
<div class="container">
    <div class="main">
        <div class="left">
            <h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎使用哈工大学生成绩管理系统</h1>
            <img class="pic1" alt="" src="image/xiaohui2.png" width="80px">
            <img class="pic2" alt="" src="image/login_school.jpg" width="608px">
        </div>
        <div class="center">
            <img alt="" src="image/login_up.png" width="422px"/>
            <form id="form" action="/Login">
                <div>
                    <label>帐&nbsp;号：&nbsp;</label> <input type="text" name="id"
                                                          placeholder="输入账号">
                </div>
                <div>
                    <label>密&nbsp;码：&nbsp;</label> <input type="password" name="password"
                                                          placeholder="输入密码">
                </div>

                <div class="formControls col-8 col-offset-3">
                    <label>验证码：</label>
                    <input class="input-text size-L" name="vcode" type="text" placeholder="请输入验证码"
                           style="width: 170px;">
                    <div>
                        <img title="点击图片切换验证码" id="vcodeImg" src="Login?method=GetVCode" style=" margin-left:0px;">
                    </div>
                </div>


                <div>
                    <label>用户身份：</label> 管理员<input type="radio" name="login"
                                                   value="admin" checked> 教师<input type="radio"
                                                                                   name="login" value="teacher" checked>
                    学生<input
                        type="radio" name="login" value="student">
                </div>
                <div>
                    <button type="button" id="submitBtn">登录</button>
                </div>
                <br>
            </form>
        </div>
    </div>
    <div class="footer">版权所有&copy;陈鹏至-刘泽群-薛景耀&amp;哈尔滨工业大学</div>
</div>
</body>
</html>
