<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>香蕉君修改学生信息</title>
</head>
<body>
	<form action="/MyDBtest/studatatest" method="post">
	学生姓名<input type = "text" name="sname" value = "${param.sname}"/><br/>
	学生地址<input type="text" name="saddress" value="${param.smajor}"/><br/>
	学生主修<input type="text" name="smajor" value="${param.saddress}"/><br/>
	学生班级<select name="gid">
		<c:forEach items="${applicationScope.grades}"  var="grade">
		<c:choose>
			<c:when test="${grade.gid==param.gid}">
				<option value="${grade.gid}" selected="selected">
				${grade.gname}
			</option>
			</c:when>
			<c:otherwise>
				<option value="${grade.gid}">
				${grade.gname}
			</c:otherwise>
		</c:choose>
		</c:forEach>
	</select><br/>
	学生爱好<c:forEach items="${applicationScope.slikes}" var ="slike">
		<c:set var="checked"/>
		<c:forTokens items="${param.slikes}" delims="," var="myLike">
			<c:if test="${myLike==slike}">
				<c:set var="checked" value="checked='checked'"/>
			</c:if>
		</c:forTokens>
		${slike}<input name ="slikes" type="checkbox" value="${slike}"  ${checked}/>
	</c:forEach><br/>
	<input type="submit" value="修改"/><br/>
	<input type="hidden" name="command" value="modify"/><br/>
	<input type="hidden" name="sid" value="${param.sid}"/>
	</form>
</body>
</html>