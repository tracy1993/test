<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<body>
		<h2>
			用户列表
		</h2>
		<table>
			<tr>
				<td>
					id
				</td>
				<td>
					姓名
				</td>
				<td>
					注册时间
				</td>
				<td>
					
				</td>
			</tr>
			<c:forEach var="user" items="${users}" varStatus="status">
				<tr>
				<td>${user.id }</td>
				<td>${user.userName }</td>
				<td>${user.signUpTime }</td>
				<td><a href="javascript:void(0)" onclick="javascript:self.location='<%=request.getContextPath() %>/usermgr/get/${user.id }.htm'">查看</a></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
