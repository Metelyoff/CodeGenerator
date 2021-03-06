<%@page import="java.util.List"%>
<%@page import="com.metelyoff.service.LockService"%>
<%@page import="com.metelyoff.service.UserService"%>
<%@page import="com.metelyoff.model.User"%>
<%@page import="com.metelyoff.model.UserLock"%>
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
		User currentUser = (User) session.getAttribute("user");
		if(currentUser==null){
			response.sendRedirect("../index.jsp");
		}else{			
			UserService us=new UserService();
			String userId = request.getParameter("userId");
			User user = us.getUserById(userId);
			LockService ls=new LockService();
			List<User> allUserList = us.getAllUsers();
			List<UserLock> lockListOfCurrentUser = ls.getLocksByUser(currentUser);
			List<UserLock> userLockList = ls.getLocksByUser(user);
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
		<h1>Locks page</h1>
		<div class="container">
			<h1>All locks of <%=user.getUserName()%> count (<%=userLockList.size()%>)</h1>
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
					<%for (UserLock l : userLockList){%>
					<tr>
						<td><%=l.getLockName()%></td>
						<td><%=l.getLockCells() %></td>
						<td><%=l.getLockRange() %></td>
						<td><%=l.getLockKey() %></td>
						<td class="col-menu"><a href="view_user_lock_keys.jsp?lockId=<%=l.getId().getIdLock()%>&userId=<%=l.getUser().getIdUser()%>">View keys</a></td>
						<td class="col-menu"><a href="delete_lock.jsp?lockId=<%=l.getId().getIdLock()%>&userId=<%=l.getId().getUserIdUser() %>">Delete lock</a></td>
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
		<%}%>
	</body>
</html>