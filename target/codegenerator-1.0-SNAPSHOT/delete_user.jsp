<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.metelyoff.model.User"%>
<%@page import="com.metelyoff.service.UserService"%>
<%
	User currentUser = (User) session.getAttribute("user");
	if(currentUser==null){
		response.sendRedirect("index.jsp");
	}else{
		UserService us=new UserService();
		us.deleteUser(currentUser);
		response.sendRedirect("user_home_page.jsp");
	}
%>