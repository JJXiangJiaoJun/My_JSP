<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setAttribute("res", "resss");
	request.setAttribute("ses", "sesss");
	%>
	<a href="http://www.hao123.com"><c:out value="${requestScope.res}"/></a>
	<a href="http://www.hao123.com"><c:out value="${requestScope.ses}"/></a>
</body>
</html>