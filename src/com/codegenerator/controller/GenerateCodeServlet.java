package com.codegenerator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codegenerator.service.CodeGenerator;
import com.codegenerator.service.LockService;
import com.codegenerator.model.Lock;

public class GenerateCodeServlet extends HttpServlet{

	private static final long serialVersionUID = -6483533922317245248L;
	
	public static HashMap<Integer,CodeGenerator> codeGenerator=new HashMap<Integer,CodeGenerator>();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId=request.getParameter("userId");
		String lockId=request.getParameter("lockId");
		String generate=request.getParameter("generateButton");
		
		LockService ls=new LockService();
		Lock lock=ls.getLockById(lockId);
		addLockToCodeGenerator(lock);
		CodeGenerator keyForLock=getCodeGeneratorByLockId(lock);
		String resultKey=keyForLock.generateKey();
		
		if(generate.equals("generate")){
			response.sendRedirect("view_user_lock_keys.jsp?userId="+userId+"&lockId="+lockId+"&key="+resultKey+"&size="+keyForLock.getList().size());
		}else{
			response.sendRedirect("error-lock.html");
		}
	}
	
	public void addLockToCodeGenerator(Lock lock){
		codeGenerator.put(lock.getId().getIdLock(), new CodeGenerator(lock));
	}
	
	public CodeGenerator getCodeGeneratorByLockId(Lock lock){
		Integer idLock=lock.getId().getIdLock();
		CodeGenerator result=null;
		for(Entry<Integer, CodeGenerator> entry : codeGenerator.entrySet()) {
		    if(idLock.equals(entry.getKey())){
		    	result=entry.getValue();
		    }else{
		    	result=null;
		    }
		}
		return result;
	}
}
