package com.metelyoff.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.metelyoff.model.User;
import com.metelyoff.service.UserService;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userMail = request.getParameter("userLoginMail");
		String password = request.getParameter("userLoginpassword");
		UserService userService = new UserService();
		boolean userResult = userService.authenticateUser(userMail, password);
		boolean adminResult = userService.authenticateAdminUser(userMail, password);
		User user = userService.getUserByMail(userMail);
		if (adminResult == true) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect("admin/admin_home_page.jsp");
		}else{
			if(userResult == true){
				request.getSession().setAttribute("user", user);
				response.sendRedirect("user_home_page.jsp");
			} else {
				response.sendRedirect("error.html");
			}
		}
	}
}