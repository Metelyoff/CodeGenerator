package com.metelyoff.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.metelyoff.model.User;
import com.metelyoff.model.UserLock;
import com.metelyoff.model.UserLockId;
import com.metelyoff.service.LockService;
import com.metelyoff.service.UserService;

public class AddLockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("id");
		String userLockName = request.getParameter("lockName");
		String userCell = request.getParameter("cell");
		String userRange = request.getParameter("range");

		int intUserCell=Integer.parseInt(userCell);
		int intUserRange=Integer.parseInt(userRange);
		
		UserService us=new UserService();
		LockService ls=new LockService();
		
		User user=us.getUserById(userId);
		UserLockId lockId=new UserLockId();
		lockId.setUserIdUser(user.getIdUser());
		UserLock lock=new UserLock(lockId,user,userLockName,intUserCell,intUserRange);
		
		boolean addResult=ls.addLock(lock,user.getIdUser());
		
		if(addResult){
			if(us.isAdminRole(user)){
				response.sendRedirect("view_user_locks.jsp?userId="+user.getIdUser());
			}else{
				response.sendRedirect("../user_home_page.jsp");
			}
		}else{
			response.sendRedirect("error-lock.html");
		}
	}
}
