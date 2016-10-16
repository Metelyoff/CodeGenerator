<%@page import="java.util.List"%>
<%@page import="com.codegenerator.service.UserService"%>
<%@page import="com.codegenerator.service.LockService"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.codegenerator.model.User"%>
<%@page import="com.codegenerator.model.UserLock"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.0.js"></script>
		<title>Admin page</title>
	</head>
	<body>
			<%
				User currentUser = (User) session.getAttribute("user");
				if(currentUser==null){
					response.sendRedirect("../index.jsp");
				}else{
				LockService ls=new LockService();
				List<UserLock> lockListOfCurrentUser = ls.getLocksByUser(currentUser);
				UserService as = new UserService();
				List<User> allUserList = as.getAllUsers();
				SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
			%>
		<nav>
		    <ul class="main-menu">
		        <li>
		        	<a href="admin_home_page.jsp"><%=currentUser.getUserName()%></a>
		        	<ul class="sub-menu">
		        		<li><a href="add_lock_to_user.jsp?userId=<%=currentUser.getIdUser()%>">Add Lock</a></li>
                    	<li><a href="view_user_locks.jsp?userId=<%=currentUser.getIdUser()%>">View locks</a></li>
                    	<li><a href="update_user.jsp?userId=<%=currentUser.getIdUser()%>">Update profile</a></li>
                    	<li><a href="delete_user.jsp?userId=<%=currentUser.getIdUser()%>" onclick="return confirm('Are you sure you want to delete your profile?');">Delete profile</a></li>
                    	<li><a href="logout.jsp">Logout</a></li>
                	</ul>
		        </li>
		        <li>
		        	<a href="admin_home_page.jsp">Users(<%=allUserList.size()%>)</a>
		        	<ul class="sub-menu">
                    	<li><a href="get-all-admin-users.jsp">Get all admin users</a></li>
                    	<li><a href="get-all-not-admin-users.jsp">Get all users</a></li>
                	</ul>
		        </li>
		        <li><a href="view_user_locks.jsp?userId=<%=currentUser.getIdUser()%>">Locks(<%=lockListOfCurrentUser.size()%>)</a></li>
		        <li><a href="logout.jsp">Logout</a></li>
		    </ul>
		</nav>
		<h1>Admin Page, Date:<%= format.format(new Date())%></h1>
		<div class="container">
			<h1>All users (<%=allUserList.size()%>)</h1>
			<table>
				<thead>
					<tr>
						<th>User Mail</th>
						<th>Name</th>
						<th>Password</th>
						<th colspan="3">Menu</th>
					</tr>
				</thead>
				<tbody>
				<%for (User u : allUserList) {%>
				<tr>
					<td><%=u.getUserMail()%></td>
					<td><%=u.getUserName()%></td>
					<td><%=u.getUserPassword()%></td>
					<td class="col-menu"><a href="view_user_locks.jsp?userId=<%=u.getIdUser()%>">View locks</a></td>
					<td class="col-menu"><a href="update_user.jsp?userId=<%=u.getIdUser()%>">Update</a></td>
					<td class="col-menu"><a href="delete_user.jsp?userId=<%=u.getIdUser()%>" onclick="return confirm('Are you sure you want to delete this profile?');">Delete</a></td>
				</tr>
				<%}%>
				<tbody>
			</table>		
			<br/>
		</div>
		<footer class="page-footer">
			<div class="copyright">
				<p><strong>Test site «Code Generator»</strong></p>
				<p>© Denys Mietielov </p>
			</div>
		</footer>
		<%}%>
	</body>
</html>
