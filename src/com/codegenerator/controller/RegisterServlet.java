package com.codegenerator.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codegenerator.model.User;
import com.codegenerator.model.UserRole;
import com.codegenerator.service.UserService;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userLoginName");
		String userMail = request.getParameter("userLoginMail");
		String password = request.getParameter("userLoginpassword");
		
		UserService userService = new UserService();
		if(userService.isUserExistsByMail(userMail)){
			response.sendRedirect("user_is_exist_error.html");
		}else{
			UserRole urUser=new UserRole();
			urUser.setIdUserRole(2);
			User user = new User(urUser,userMail, password, userName);
			try {
				boolean result = userService.addUser(user);
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Registration Successful</title>");
				out.println("<link type='text/css' rel='stylesheet' href='css/admin.css' />");
				out.println("</head>");
				out.println("<body>");
				out.println("<center>");
				if (result) {
					out.println("<h1>Thanks for Registering with us :</h1>");
					out.println("<h1>To login <a href=index.jsp>Click here</a></h1>");
				} else {
					out.println("<h1>Registration Failed</h1>");
					out.println("<h1>To try again<a href=signup.jsp>Click here</a></h1>");
				}
				out.println("</center>");
				out.println("</body>");
				out.println("</html>");
			} finally {
				out.close();
			}
		}
	}
}