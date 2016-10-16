package com.metelyoff.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.metelyoff.hibernate.util.HibernateUtil;
import com.metelyoff.model.User;

public class RoleService {
	public int getUserRoleIDByUser(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int userRoleID=0;
		try {
			tx = session.getTransaction();
			tx.begin();//select u.userRole.userRoleName from User u where u.idUser='1'
			Query query = session.createQuery("select u.userRole.idUserRole from User u where u.idUser='" + user.getIdUser() + "'");
			userRoleID = (Integer) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return userRoleID;
	}
	
	public String getUserRoleNameByUser(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		String userRoleName=null;
		try {
			tx = session.getTransaction();
			tx.begin();//select u.userRole.userRoleName from User u where u.idUser='1'
			Query query = session.createQuery("select u.userRole.userRoleName from User u where u.idUser='" + user.getIdUser() + "'");
			userRoleName = (String) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return userRoleName;
	}
	
	public int getOtherUserRoleIDByUser(User user){
		if(getUserRoleIDByUser(user)==1){
			return 2;
		}else{
			return 1;
		}
	}
	
	public String getOtherUserRoleNameByUser(User user){
		if(getUserRoleNameByUser(user).equals("admin")){
			return "user";
		}else{
			return "admin";
		}
	}
}
