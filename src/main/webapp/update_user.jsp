<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.metelyoff.service.LockService"%>
<%@page import="com.metelyoff.model.User"%>
<%@page import="com.metelyoff.model.Lock"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/update-admin-form.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.0.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/signup.js"></script>
		<title>Update user form</title>
	</head>
	<body>
		<%
		User currentUser = (User) session.getAttribute("user");
		if(currentUser==null){
			response.sendRedirect("index.jsp");
		}else{
			LockService ls=new LockService();
			List<Lock> currentUserLockList = ls.getLocksByUser(currentUser);
		%>
		<nav>
		    <ul class="main-menu">
		        <li>
		        	<a href="user_home_page.jsp"><%=currentUser.getUserName()%></a>
		        	<ul class="sub-menu">
		        		<li><a href="add_lock_to_user.jsp">Add Lock</a></li>
                    	<li><a href="user_home_page.jsp">View locks</a></li>
                    	<li><a href="update_user.jsp">Update profile</a></li>
                    	<li><a href="delete_user.jsp" onclick="return confirm('Are you sure you want to delete your profile?');">Delete profile</a></li>
                    	<li><a href="logout.jsp">Logout</a></li>
                	</ul>
		        </li>
		        <li><a href="user_home_page.jsp">Locks(<%=currentUserLockList.size()%>)</a></li>
		        <li><a href="logout.jsp">Logout</a></li>
		    </ul>
		</nav>
		<h1>Update <%=currentUser.getUserName()%> user form</h1>
		<center>
		<div class="login-block">
			<form action="${pageContext.request.contextPath}/UpdateUserServlet" method="POST">
				<h1 class="login-label">Update</h1>
				<input type="hidden" name="id" value="<%=currentUser.getIdUser()%>"/>
				<input name="userName" type="text" title="Enter your name" value="<%=currentUser.getUserName()%>" size="15" maxlength="15" placeholder="Name" required/>     
				<input name="userMail" type="text" title="Enter your mail" value="<%=currentUser.getUserMail()%>" size="45" maxlength="45" placeholder="Mail" required/>
				<input id="txtuserLoginpassword" name="password" type="password" title="Enter your password" size="30" maxlength="30" placeholder="Password" required/>  
				<input id="txtuserLoginpasswordConfirm" onChange="checkPasswordMatch();" name="password" type="password" title="Confirm your password" size="30" maxlength="30" placeholder="Confirm your password" required/>    
				<div id="divCheckPasswordMatch" class="confirm-password"></div>
				<label id="login-label-image" for="login">Press on the lock to update</label>
				<input name="Update" type="image" src="${pageContext.request.contextPath}/images/lock.png" title="Update"/>  
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