<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.codegenerator.model.Lock"%>
<%@page import="com.codegenerator.model.User"%>
<%@page import="com.codegenerator.service.LockService"%>
<%
	String userId = request.getParameter("userId");
	LockService ls=new LockService();
	String lockId = request.getParameter("lockId");
	Lock l = ls.getLockById(lockId);
	ls.deleteLock(l);
	response.sendRedirect("view_user_locks.jsp?userId="+userId);
%>