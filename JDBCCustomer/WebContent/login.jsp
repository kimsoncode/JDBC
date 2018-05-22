<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<center>
<h5>用户登录</h5>
<fieldset>
<legend>登录后并跳转到添加数据页面</legend>
<form action="LoginServlet" method="post">
        用户名：<input type="text" name="username" style="wdith:190px"><br>
     密&nbsp;码：<input type="password" name="password" style="wdith:190px"><br>
       <input type="submit" value="登录">
      </form>
</fieldset>
</center>
</body>
</html>