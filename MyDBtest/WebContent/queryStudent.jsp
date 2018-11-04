<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="StudentModel.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>香蕉君的查询学生数据</title>
</head>
<body>
<form action ="/MyDBtest/studatatest"  method ="post">
	关键字<input type ="text" name="studentname"/><br/>
	<input type ="submit"  name ="command" value="query"/><br/>
</form>

<!-- --------------JSTL版本------------------- -->


<table border="1">
	<tr>
	<td>编号</td>
	<td>姓名</td>
	<td>地址</td>
	<td>主修</td>
	<td>爱好</td>
	<td>班级</td>
	<td>修改</td>
	<td>删除</td>
	</tr>
	
	<c:forEach items="${requestScope.students}" var="student" varStatus="stuStatus">
	
	<tr>
		<td>${stuStatus.count}</td>
		<td>${student.sname}</td>
		<td>${student.saddress}</td>
		<td>${student.smajor}</td>
		<td>${student.slikes}</td>
		<td>${student.grade.gname}</td>
		<td>
		<c:url value="/modifyStudent.jsp" var="modify">
			<c:param name="sid" value = "${student.id}"/>
			<c:param name="sname" value ="${student.sname }"/>
			<c:param name="saddress" value="${student.saddress}"/>
			<c:param name="smajor" value="${student.smajor}"/>
			<c:param name="slikes" value="${student.slikes}"/>
			<c:param name="gid"   value="${student.grade.gid}"/>
			<c:param name="gname" value="${student.grade.gname}"/>
		</c:url>
		<a href="${modify}">修改</a>
		</td>
		
		<td>
		<c:url value="/studatatest" var="delete">
			<c:param name="studentId" value = "${student.id}"/>
			<c:param name="command" value ="delete"/>
		</c:url>
		<a href="${delete}">删除</a>
		</td>
	</tr>
	
	</c:forEach>
	
</table><br/>
<a href="/MyDBtest/studatatest?command=split&currentPage=1">首页</a>
<c:choose>
	<c:when test="${sessionScope.currentPage==1}">
		上一页
	</c:when>
	<c:otherwise>
		<a href="/MyDBtest/studatatest?command=split&currentPage=${sessionScope.currentPage-1}">上一页</a>
	</c:otherwise>
</c:choose>	

<c:choose>
	<c:when test="${sessionScope.currentPage==sessionScope.totalPage}">
		下一页
	</c:when>
	<c:otherwise>
		<a href="/MyDBtest/studatatest?command=split&currentPage=${sessionScope.currentPage+1}">下一页</a>
	</c:otherwise>
</c:choose>	

<a href="/MyDBtest/studatatest?command=split&currentPage=${sessionScope.totalPage}">尾页</a>
<script type="text/javascript">
	function changePage()
	{
		var currentPage = document.getElementById("currentPage").value;
		window.open("/MyDBtest/studatatest?command=split&currentPage=" + currentPage,"_self")
	}
</script>


<select id = "currentPage" onchange="changePage()">
	<c:forEach begin="1" end="${sessionScope.totalPage}" step="1" var="currentPage">
		<c:choose>
			<c:when test="${sessionScope.currentPage==currentPage}">
				<option value="${currentPage}" selected="selected">${currentPage}</option>
			</c:when>
			<c:otherwise>
				<option value="${currentPage}">${currentPage}</option>
			</c:otherwise>
		</c:choose> 
	</c:forEach>
</select>

</body>
</html>