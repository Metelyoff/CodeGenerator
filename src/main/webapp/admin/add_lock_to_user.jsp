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
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/indexstyle.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/locks-table.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.0.js"></script>
		<title>Add lock page</title>
	</head>
	<body>
		<%
		User currentUser = (User) session.getAttribute("user");
		if(currentUser==null){
			response.sendRedirect("../index.jsp");
		}else{
			LockService ls=new LockService();
			List<UserLock> lockListOfCurrentUser = ls.getLocksByUser(currentUser); 
			UserService userService = new UserService();
			List<User> allUserList = userService.getAllUsers();
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
		<h1>Add lock form to <%=currentUser.getUserName()%> user</h1>
		<center>
			<div class="login-block">
				<form action="${pageContext.request.contextPath}/admin/AddLockServlet" method="POST">
					<h1 class="login-label">Add lock</h1>
					<input type="hidden" name="id" value="<%=currentUser.getIdUser()%>"/>
					<input name="lockName" type="text" title="Enter your lock name" size="15" maxlength="15" placeholder="Lock Name" required/>
					<br>
					<label for="numberCells">Number of cells:</label>
					<br>
					<select type="text" id="numberCells" name="cell" class="select-cell">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
					</select>
					<br>
					<label for="numberRange">Number of values:</label>
					<br>
					<select type="text" id="numberRange" name="range" class="select-range">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
					<br>   
					<label id="login-label-image" for="login">Press on the lock to add</label>
					<input name="Add" type="image" src="${pageContext.request.contextPath}/images/lock.png" title="ADD"/>  
				</form>
			</div>
		</center>
		<footer class="page-footer">
			<div class="copyright">
				<p><strong>Test site «Code Generator»</strong></p>
				<p>© Denys Mietielov </p>
			</div>
		</footer>
		<%}%>
	</body>
</html>