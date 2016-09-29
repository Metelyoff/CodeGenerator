<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="com.codegenerator.model.User"%>
	<%@page import="com.codegenerator.service.UserService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Update user Form</title>
<style type="text/css">
h3 {
	font-family: Calibri;
	font-size: 22pt;
	font-style: normal;
	font-weight: bold;
	color: SlateBlue;
	text-align: center;
	text-decoration: underline
}

table {
	font-family: Calibri;
	color: white;
	font-size: 11pt;
	font-style: normal;
	width: 50%;
	text-align:;
	background-color: SlateBlue;
	border-collapse: collapse;
	border: 2px solid navy
}

table.inner {
	border: 0px
}
</style>
</head>
<body>
	<%
		UserService uus=new UserService();
		String userId = request.getParameter("userId");
		User u = uus.getUserById(userId);
	%>
	<h3>User Update Form</h3>
	<form action="UpdateUserServlet" method="POST">

		<table align="center" cellpadding="10">
			<input type="hidden" name="id" value="<%=u.getId_user()%>"/>
			<tr>
				<td>User mail</td>
				<td><input type="text" name="userMail" maxlength="30" value="<%=u.getUser_mail()%>"/> (max
					30 characters a-z and A-Z)</td>
			</tr>
			<tr>
				<td>User Name</td>
				<td><input type="text" name="userName" maxlength="30" value="<%=u.getUser_name()%>"/>
					(max 30 characters a-z and A-Z)</td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" maxlength="100" placeholder="Enter the new password"/></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Submit"> <input type="reset" value="Reset">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>