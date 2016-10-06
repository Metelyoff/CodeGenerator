package com.codegenerator.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codegenerator.model.User;
import com.codegenerator.service.UserService;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userLoginName");
		String userMail = request.getParameter("userLoginMail");
		String password = request.getParameter("userLoginpassword");
		User user = new User(userMail, password, userName);

		try {
			UserService registerService = new UserService();
			boolean result = registerService.addUser(user);
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Registration Successful</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<center>");
			if (result) {
				out.println("<h1>Thanks for Registering with us :</h1>");
				out.println("To login with new UserId and Password<a href=index.jsp>Click here</a>");
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