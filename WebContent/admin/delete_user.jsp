<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.codegenerator.model.User"%>
<%@page import="com.codegenerator.service.AdminService"%>
<%
	AdminService as=new AdminService();
	String userId = request.getParameter("userId");
	User u = as.getAdminUserById(userId);
	as.deleteAdminUser(u);
	response.sendRedirect("admin_home_page.jsp");
%>