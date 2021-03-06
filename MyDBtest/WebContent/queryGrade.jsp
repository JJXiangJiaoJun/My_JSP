<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>香蕉君查询班级信息</title>
</head>
<body>
<form action ="/MyDBtest/grade" method="post">
	<input type="text" name="gname"/>
	<input type="submit" name="提交"/>
	<input type="hidden" name="command" value="query"/>
</form>
<table border="1">
	<tr>
	<td>编号</td>
	<td>班级id</td>
	<td>班级名称</td>
	<td>修改</td>
	<td>删除</td>
	</tr>
	<c:forEach items="${requestScope.grades}"  var="grade" varStatus="gradeStatus">
		<tr>
		<td>${gradeStatus.count}</td>
		<td>${grade.gid }</td>
		<td>${grade.gname}</td>
		<td>
			<c:url value="/modifyGrade.jsp" var ="update">
				<c:param name="gid" value="${grade.gid }"/>
				<c:param name="gname" value="${grade.gname}"/>
			</c:url>
		<a href="${update}">修改</a>		
		</td>
		<td>
			<c:url value="/modifyGrade.jsp" var ="delete">
				<c:param name="gid" value="${grade.gid }"/>
				<c:param name="command" value="delete"/>
			</c:url>
			<a href="${delete}">删除</a>			
		</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>