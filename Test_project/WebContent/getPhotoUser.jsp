<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<sql:setDataSource var="webappDataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/rvcdb" user="root" password="" />

<sql:query dataSource="${webappDataSource}" sql="select id,username from tbl_user" var="result" />

<table width="100%" border="1">
	<c:forEach var="row" items="${result.rows}">
		<tr>
			<td>${row.id}</td>
			<td>${row.username}</td>
			<td><img src="PhotoServlet?U=${row.username}" width="50" height="80"  /></td>
		</tr>
	</c:forEach>
</table>