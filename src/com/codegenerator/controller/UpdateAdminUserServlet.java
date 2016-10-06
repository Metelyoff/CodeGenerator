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
		
		try {
			AdminService updateService = new AdminService();
			User user=updateService.getAdminUserById(userId);
			user.setUserName(userName);
			user.setUserMail(userMail);
			user.setUserRole(userRole);
			user.setUserPassword(password);
			boolean result = updateService.editAdminUser(user);
			/*out.println("<html>");
			out.println("<head>");
			out.println("<title>Update Successful</title>");
			out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			out.println("<link type='text/css' rel='stylesheet' href='../css/admin.css'/>");
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
			out.println("</html>");*/
			if (result == true) {
				request.getSession().setAttribute("user", user);
				response.sendRedirect("admin_home_page.jsp");
			} else {
				response.sendRedirect("error.jsp");
			}
		} finally {
			out.close();
		}
	}
}
