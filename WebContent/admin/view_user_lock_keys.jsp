<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="com.codegenerator.service.LockService"%>
<%@page import="com.codegenerator.service.UserService"%>
<%@page import="com.codegenerator.service.KeyService"%>
<%-- <%@page import="com.codegenerator.service.CodeGenerator"%> --%>
<%@page import="com.codegenerator.model.User"%>
<%@page import="com.codegenerator.model.UserLock"%>
<%@page import="com.codegenerator.model.LockKey"%>
<%@page import="com.codegenerator.model.LockKeyId"%>
<%@page import="com.codegenerator.model.CodeGenerator"%>
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
			String lockId = request.getParameter("lockId");
			UserLock lock = ls.getLockById(lockId);
			
			KeyService ks=new KeyService();
			
			List<User> allUserList = us.getAllUsers();
			List<UserLock> currentUserList = ls.getLocksByUser(currentUser);
			List<LockKey> currentUserLockKeys=ks.getAllKeysByLock(lock);
			
			String key=request.getParameter("key");
			/* String next=request.getParameter("next");
			String size=request.getParameter("size");
			int nextInt;
			int count;
			if(next==null){
				nextInt=10;
				count=0;
			}else{
				nextInt=Integer.parseInt(next);
				count=nextInt-10;
			} */
		%>
		<nav>
		    <ul class="main-menu">
		        <li>
		        	<a href="admin_home_page.jsp"><%=currentUser.getUserName()%></a>
		        	<ul class="sub-menu">
		        		<li><a href="add_lock_to_current_user.jsp?userId=<%=currentUser.getIdUser()%>">Add Lock</a></li>
                    	<li><a href="view_user_locks.jsp?userId=<%=currentUser.getIdUser()%>">View locks</a></li>
                    	<li><a href="update_current_user.jsp?userId=<%=currentUser.getIdUser()%>">Update profile</a></li>
                    	<li><a href="delete_current_user.jsp?userId=<%=currentUser.getIdUser()%>" onclick="return confirm('Are you sure you want to delete your profile?');">Delete profile</a></li>
                    	<li><a href="logout.jsp">Logout</a></li>
                	</ul>
		        </li>
		        <li>
		        	<a href="admin_home_page.jsp">Users(<%=currentUserList.size()%>)</a>
		        	<ul class="sub-menu">
                    	<li><a href="get-all-admin-users.jsp">Get all admin users</a></li>
                    	<li><a href="get-all-not-admin-users.jsp">Get all users</a></li>
                	</ul>
		        </li>
		        <li><a href="view_user_locks.jsp?userId=<%=currentUser.getIdUser()%>">Locks(<%=currentUserList.size()%>)</a></li>
		        <%--<li>
		        	<a href="view_user_lock_keys.jsp?userId=<%=currentUser.getIdUser()%>">Keys(<%=correntUserLockKeys.size()%>)</a>
		        	 <ul class="sub-menu">
                    	<li><a href="generate_key_method.jsp?userId=<%=currentUser.getIdUser()%>&lockId=<%=lockId%>&generate=all">Generate all keys</a></li>
                    	<li><a href="generate_key_method.jsp?userId=<%=currentUser.getIdUser()%>&lockId=<%=lockId%>&generate=key">Generate key</a></li>
                	</ul> 
		        </li>--%>
		        <li><a href="logout.jsp">Logout</a></li>
		    </ul>
		</nav>
		<h1>Generate key page</h1>
		<div class="container">
			<h1>Generate key for <%=lock.getLockName()%> lock, count keys (<%=currentUserLockKeys.size()%>)</h1>
			<form action="${pageContext.request.contextPath}/admin/GenerateCodeServlet" method="POST">
				<h1>key</h1>
				<input type="hidden" name="userId" value="<%=currentUser.getIdUser()%>"/>
				<input type="hidden" name="lockId" value="<%=lock.getId().getIdLock()%>"/>
				<h1><%=key %></h1>
				<button type="submit" title="GENERATE" name="generateButton" value="generate">GENERATE</button>
				<!-- view_user_lock_keys.jsp?userId=<%-- <%=currentUser.getIdUser()%> --%>&lockId=<%-- <%=lockId%> --%>& -->
			</form>
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