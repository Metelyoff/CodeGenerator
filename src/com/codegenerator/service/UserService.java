package com.codegenerator.service;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.codegenerator.hibernate.util.HibernateUtil;
import com.codegenerator.model.User;
import com.codegenerator.model.Lock;

public class UserService {
	 
	public boolean authenticateUser(String userMail, String password) {
		User user = getUserByMail(userMail);
		if (user != null && user.getUser_mail().equals(userMail) && user.getUser_password().equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	
	public User getUserById(String userId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query<User> query = session.createQuery("from User where id_user='" + userId + "'");
			user = query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
	
	public User getUserByMail(String userMail) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.getTransaction();
			tx.begin();//
			Query<User> query = session.createQuery("from User where user_mail='" + userMail + "'");
			user = query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	public List<User> getAllUsers() {
		List<User> list = new ArrayList<User>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			list = (List<User>)session.createQuery("from User").list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public boolean addUser(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (isUserExists(user))
			return false;

		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.saveOrUpdate(user);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return true;
	}

	public boolean isUserExists(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean result = false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query<User> query = session.createQuery("from User where id_user='" + user.getId_user() + "'");
			User u = query.uniqueResult();
			tx.commit();
			if (u != null)
				result = true;
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return result;
	}
	
	public boolean deleteUser(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (!isUserExists(user))
			return false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query<User> query = session.createQuery("from User where id_user='" + user.getId_user() + "'");
			/*User u = (User) query.uniqueResult();*/
			/*ArrayList<Lock> locks=u.getLocks();*/
			session.delete(user);
			/*for(Lock lock:locks){
				session.delete(lock);
			}*/
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return true;
	}
	
	public boolean editUser(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (!isUserExists(user))
			return false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.update(user);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return true;
	}
}
