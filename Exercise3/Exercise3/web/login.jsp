<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Cookie[] cc = request.getCookies();
    String uid = "";
    String pwd = "";
    for (Cookie c : cc) {
        if(c.getName().equals("uid")) {
            uid = c.getValue();
        } else if(c.getName().equals("pwd")) {
            pwd = c.getValue();
        }
    }
%>
<form method="post" action="LoginServlet">
    <table border = "1">
        <tr>
            <td>用户名</td>
            <td><input type="text" name="uid" value="<%=uid%>"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="pwd" value="<%=pwd%>"></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="登录">
                <input type="checkbox" name="remember" value="1">记住我
            </td>
        </tr>
    </table>
</form>
</body>
</html>
