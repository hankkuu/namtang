<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>Login with Username and Password</h1>
<form action="${pageContext.request.contextPath}/adminLoginForm" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
<table style="width: 350px">
	<tr>
		<td width="70px">ID</td>
		<td><input type="text" name="username" size="30"></td>
	</tr>
	<tr>
		<td>Password</td>
		<td><input type=password name="password" size="31"></td>
	</tr>
	
	<tr>
		<td colspan="2"><input type="submit" value="로그인"></td>
	</tr>
</table>
</form>
</body> 
</html>