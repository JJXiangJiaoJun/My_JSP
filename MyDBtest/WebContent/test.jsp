<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
<head>
<title>香蕉君的数据库测试程序</title>
</head>
<body>

   <form action="/MyDBtest/studatatest" method = "post">
  	学生姓名： <input type="text" name = "sname"/><br/>
   	学生地址： <input type ="text" name = "saddress"/><br/>
   	学生主修:  <input type= "text" name = "smajor"/><br/>
   	学生班级:  <select name="gid">
   			<c:forEach items= "${applicationScope.grades}" var="grade">
   				<option value="${grade.gid}">${grade.gname}</option>
   			</c:forEach>
   			</select><br/>
   	学生爱好:<c:forEach items="${applicationScope.slikes}" var="slike">
   			${slike}:<input type ="checkbox" name="slikes" value="${slike}"/> 
   			</c:forEach><br/>
  	<input type="hidden" name = "command" value="save"/>
   	<input type = "submit" value="保存"/><br/>
   </form>
   
   
</body>
</html>