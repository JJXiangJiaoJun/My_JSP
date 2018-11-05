<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>香蕉君的自动登录界面</title>
</head>
<body>
<form action="/MyDBtest/AdminServlet" method= "post">
	用户名:<input type="text" name="username"/><br/>
	密码:<input type="text" name="passwd"/><br/>
	<font color=red>${param.error}!!!</font><input type="submit" value="登录"/>
	自动登录:<input type="checkbox" name="autologin" value="on"/>
	<select name="maxage">
		<option  value="1">一天</option>
		<option  value="30">一个月</option>
		<option value="365">一年</option>
	</select>
	<input type="hidden" name="command" value="login"/>
</form>
</body>
</html>