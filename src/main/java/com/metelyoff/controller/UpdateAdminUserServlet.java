package com.metelyoff.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.metelyoff.model.User;
import com.metelyoff.model.UserRole;
import com.metelyoff.service.UserService;

public class UpdateAdminUserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("id");
		String userName = request.getParameter("userName");
		String userMail = request.getParameter("userMail");
		String userRole = request.getParameter("userRole");
		String password = request.getParameter("password");
		
		int idUserRole=Integer.parseInt(userRole);
		
		UserService userService = new UserService();
		try {
			User user=userService.getUserById(userId);
			UserRole ur=new UserRole();
			ur.setIdUserRole(idUserRole);
			user.setUserName(userName);
			user.setUserMail(userMail);
			user.setUserRole(ur);
			user.setUserPassword(password);
			boolean result = userService.editUser(user);
			if (result == true) {
				/*request.getSession().setAttribute("user", user);*/
				response.sendRedirect("admin_home_page.jsp");
			} else {
				response.sendRedirect("error.jsp");
			}
		} finally {
			out.close();
		}
	}
}
