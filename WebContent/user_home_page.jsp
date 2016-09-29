<%@page import="java.util.List"%>
<%@page import="com.codegenerator.service.LockService"%>
<%@page import="java.util.Date"%>
<%@page import="com.codegenerator.model.User"%>
<%@page import="com.codegenerator.model.Lock"%>
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
			 <b>Welcome <%= user.getUser_name() + " - " + user.getUser_mail()%></b>		
			 <br/>
			 <a href="logout.jsp">Logout</a>
		 </p>

		 <table>
			 <thead>
				 <tr>
					 <th>Lock Name</th>
					 <th>Lock cells</th>
					 <th>Lock range</th>
					 <th>Lock key</th>				
				 </tr>
			 </thead>
			 <tbody>
			 	<%
			 		LockService ls=new LockService();
					List<Lock> list = ls.getLocksByUser(user);
					for (Lock l : list) {
				 %>
				 <tr>
					 <td><%=l.getLock_name() %></td>
					 <td><%=l.getLock_cells() %></td>
					 <td><%=l.getLock_range() %></td>
					 <td><%=l.getLock_key() %></td>
				 </tr>
				 <%}%>
			 <tbody>
		 </table>		
		 <br/>
		 <%-- <h1><%=ls.getLockById("1").getLock_name() %></h1> --%>
	 </div>
	</center>	
</body>
</html>
