<%@page import="java.util.List"%>
<%@page import="com.codegenerator.service.LockService"%>
<%@page import="com.codegenerator.service.AdminService"%>
<%@page import="java.util.Date"%>
<%@page import="com.codegenerator.model.User"%>
<%@page import="com.codegenerator.model.Lock"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/locks-table.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.0.js"></script>
		<title>Admin locks page</title>
	</head>
	<body>
		<%
			AdminService as=new AdminService();
			String userId = request.getParameter("userId");
			User user = as.getAdminUserById(userId);
			LockService ls=new LockService();
			List<Lock> lockList = ls.getLocksByUser(user);
		%>
		<nav>
		    <ul class="main-menu">
		        <li>
		        	<a href="admin_home_page.jsp"><%=user.getUserName()%></a>
		        	<ul class="sub-menu">
		        		<li><a href="add_lock_to_current_user.jsp">Add Lock</a></li>
                    	<li><a href="view_current_user_locks.jsp">View locks</a></li>
                    	<li><a href="update_current_user.jsp">Update profile</a></li>
                    	<li><a href="delete_current_user.jsp" onclick="return confirm('Are you sure you want to delete your profile?');">Delete profile</a></li>
                    	<li><a href="logout.jsp">Logout</a></li>
                	</ul>
		        </li>
		        <li>
		        	<a href="admin_home_page.jsp">Users</a>
		        	<ul class="sub-menu">
                    	<li><a href="get-all-admin-users.jsp">Get all admin users</a></li>
                    	<li><a href="get-all-not-admin-users.jsp">Get all users</a></li>
                	</ul>
		        </li>
		        <li><a href="add_lock_to_current_user.jsp">Add Lock</a></li>
		        <li><a href="view_current_user_locks.jsp">Locks(<%=lockList.size()%>)</a></li>
		        <li><a href="logout.jsp">Logout</a></li>
		    </ul>
		</nav>
		<h1>Admin locks page</h1>
		<div class="container">
			<h1>All locks of <%=currentUser.getUserName()%></h1>
			<table>
				<thead>
					<tr>
						<th>Lock name</th>
						<th>Lock cells</th>
						<th>Lock range</th>
						<th>Lock key</th>
						<th colspan="2">Menu</th>
					</tr>
				</thead>
				<tbody>
					<%for (Lock l : lockList){%>
					<tr>
						<td><%=l.getLockName()%></td>
						<td><%=l.getLockCells() %></td>
						<td><%=l.getLockRange() %></td>
						<td><%=l.getLockKey() %></td>
						<td class="col-menu"><a href="#">View keys</a></td>
						<td class="col-menu"><a href="#">Delete lock</a></td>
					</tr>
					<%}%>
				<tbody>
			</table>
		</div>
		<footer class="page-footer">
			<div class="copyright">
				<p><strong>Test site «Code Generator»</strong></p>
				<p>© Denys Mietielov </p>
			</div>
		</footer>
	</body>
</html>