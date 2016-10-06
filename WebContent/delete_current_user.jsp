<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.codegenerator.model.User"%>
<%@page import="com.codegenerator.service.AdminService"%>
<%
	AdminService as=new AdminService();
	User currentUser = (User) session.getAttribute("user");
	as.deleteAdminUser(currentUser);
	response.sendRedirect("../index.jsp");
%>