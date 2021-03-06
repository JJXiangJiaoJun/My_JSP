<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>香蕉君登录的后台界面</title>
</head>
<body>

<c:choose>
	<c:when test="${sessionScope.username!=null}">
	 欢迎回来！<br/>
	 ${sessionScope.username}<br/>
	 <a href="/MyDBtest/AdminServlet?command=deleteCookies">注销</a>
	</c:when>
	
	<c:otherwise>
		<c:url value="/login.jsp" var="login" >
			<c:param name="error" value="还未登录，请先登录"/>
		</c:url>
		您未登录，请点击这里进行<a href="${login}">登录</a><br/>
	</c:otherwise>
</c:choose>

</body>
</html>