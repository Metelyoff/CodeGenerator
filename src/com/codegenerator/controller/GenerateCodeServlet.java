package com.codegenerator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codegenerator.model.CodeGenerator;
import com.codegenerator.model.LockKey;
import com.codegenerator.model.LockKeyId;
import com.codegenerator.service.KeyService;
import com.codegenerator.service.LockService;
import com.codegenerator.model.UserLock;

public class GenerateCodeServlet extends HttpServlet{

	private static final long serialVersionUID = -6483533922317245248L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId=request.getParameter("userId");
		String lockId=request.getParameter("lockId");
		String generate=request.getParameter("generateButton");
		
		LockService ls=new LockService();
		KeyService ks=new KeyService();
		
		UserLock lock=ls.getLockById(lockId);
		
		CodeGenerator cg=new CodeGenerator(lock);
		
		LockKeyId keyId=new LockKeyId();
		keyId.setLockIdLock(lock.getId().getIdLock());
		keyId.setLockUserIdUser(lock.getUser().getIdUser());
		
		String keyResult=cg.generateKey();
		LockKey key=new LockKey(keyId,lock,keyResult);
		boolean addResult=ks.addKeyByLockId(key, lock);
		
		if(addResult){
			if(generate.equals("generate")){
				response.sendRedirect("view_user_lock_keys.jsp?userId="+userId+"&lockId="+lockId+"&key="+key.getKeyCode());
			}else{
				response.sendRedirect("error-lock.html");
			}
		}else{
			response.sendRedirect("error-lock.html");
		}
	}
}
