<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>香蕉君的班级插入页面</title>
</head>
<body>
<form action="/MyDBtest/grade" method="post">
	班级<input type="text"  name="gname"/><br/>
	<input type ="submit" name="command" value="save"/><br/>
</form>
</body>
</html>