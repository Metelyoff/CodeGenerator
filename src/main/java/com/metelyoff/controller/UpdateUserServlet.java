package com.metelyoff.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.metelyoff.model.User;
import com.metelyoff.service.UserService;;

public class UpdateUserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("id");
		String userName = request.getParameter("userName");
		String userMail = request.getParameter("userMail");
		String password = request.getParameter("password");
		
		try {
			UserService updateService = new UserService();
			User user=updateService.getUserById(userId);
			user.setUserName(userName);
			user.setUserMail(userMail);
			user.setUserPassword(password);
			boolean result = updateService.editUser(user);
			if (result == true) {
				request.getSession().setAttribute("user", user);
				response.sendRedirect("user_home_page.jsp");
			} else {
				response.sendRedirect("error.html");
			}
		} finally {
			out.close();
		}
	}
}
