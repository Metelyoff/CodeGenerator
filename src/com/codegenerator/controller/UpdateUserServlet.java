package com.codegenerator.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codegenerator.model.User;
import com.codegenerator.service.UserService;;

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
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Update Successful</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<center>");
			if (result) {
				out.println("<h1>Update Successful</h1>");
				out.println("To login with new User and Password<a href=index.jsp>Click here</a>");
			} else {
				out.println("<h1>Registration Failed</h1>");
				out.println("To try again<a href=signup.jsp>Click here</a>");
			}
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}
}
