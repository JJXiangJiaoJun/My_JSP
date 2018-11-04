<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>香蕉君修改班级信息</title>
</head>
<body>
	<form action="/MyDBtest/grade" method="post">
	班级名称:<input type="text"  name ="gname" value="${param.gname}"/><br/>
	<input type="submit" value="修改"/>
	<input type="hidden" name="command" value="update"/>
	<input type="hidden" name="gid" value="${param.gid }"/>
	</form>
</body>
</html>