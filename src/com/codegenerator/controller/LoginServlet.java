package com.codegenerator.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codegenerator.model.User;
import com.codegenerator.service.AdminService;
import com.codegenerator.service.UserService;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userMail = request.getParameter("userLoginMail");
		String password = request.getParameter("userLoginpassword");
		UserService loginService = new UserService();
		AdminService adminLoginService = new AdminService();
		boolean userResult = loginService.authenticateUser(userMail, password);
		boolean adminResult = adminLoginService.authenticateAdminUser(userMail, password);
		User user = adminLoginService.getAdminUserByMail(userMail);
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