<%@page import="java.util.List"%>
<%@page import="com.codegenerator.service.UserService"%>
<%@page import="java.util.Date"%>
<%@page import="com.codegenerator.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <title>Result Page</title>	
</head>
<body>
<center>
	 <div id="container">
		 <h1>Result Page</h1>
			 <b>This is Sample Result Page</b><br/>
			 <%=new Date()%></br>
			 <%
				 User user = (User) session.getAttribute("user");
			 %>		
			 <b>Welcome <%= user.getUser_name() + " " + user.getUser_mail()%></b>		
			 <br/>
			 <a href="logout.jsp">Logout</a>
		 </p>

		 <table>
			 <thead>
				 <tr>
					 <th>User Mail</th>
					 <th>Name</th>
					 <th>Password</th>				
				 </tr>
			 </thead>
			 <tbody>
				 <%
					 UserService loginService = new UserService();
					 List<User> list = loginService.getAllUsers();
					 for (User u : list) {
				 %>
				 <tr>
					 <td><%=u.getUser_mail()%></td>
					 <td><%=u.getUser_name()%></td>
					 <td><%=u.getUser_password()%></td>
					 <td><a href="update.jsp?userId=<%=u.getId_user()%>">Update</a></td>
					 <td><a href="delete.jsp?userId=<%=u.getId_user()%>">Delete</a></td>
				 </tr>
				 <%}%>
			 <tbody>
		 </table>		
		 <br/>
	 </div>
	</center>	
</body>
</html>
