<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.codegenerator.model.User"%>
<%@page import="com.codegenerator.service.UserService"%>

<%
UserService us=new UserService();
String userId = request.getParameter("userId");
User u = us.getUserById(userId);
us.deleteUser(u);
response.sendRedirect("home.jsp");
%>