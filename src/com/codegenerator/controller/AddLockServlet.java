package com.codegenerator.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codegenerator.model.User;
import com.codegenerator.model.Lock;
import com.codegenerator.model.LockId;
import com.codegenerator.service.AdminService;
import com.codegenerator.service.LockService;

public class AddLockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("id");
		String userLockName = request.getParameter("lockName");
		String userCell = request.getParameter("cell");
		String userRange = request.getParameter("range");

		int intUserCell=Integer.parseInt(userCell);
		int intUserRange=Integer.parseInt(userRange);
		
		AdminService as=new AdminService();
		LockService ls=new LockService();
		
		User user=as.getAdminUserById(userId);
		LockId lockId=new LockId();
		lockId.setUserIdUser(user.getIdUser());
		Lock lock=new Lock(lockId,user,userLockName,intUserCell,intUserRange);
		
		boolean addResult=ls.addLock(lock,user.getIdUser());
		
		if(addResult){
			response.sendRedirect("view_user_locks.jsp?userId="+user.getIdUser());
		}
	}
}
