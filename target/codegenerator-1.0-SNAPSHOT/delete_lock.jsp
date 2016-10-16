<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.metelyoff.model.Lock"%>
<%@page import="com.metelyoff.model.User"%>
<%@page import="com.metelyoff.service.LockService"%>
<%
	LockService ls=new LockService();
	String lockId = request.getParameter("lockId");
	Lock l = ls.getLockById(lockId);
	ls.deleteLock(l);
	response.sendRedirect("user_home_page.jsp");
%>