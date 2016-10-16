package com.codegenerator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.codegenerator.hibernate.util.HibernateUtil;
import com.codegenerator.model.UserLock;
import com.codegenerator.model.User;
import com.codegenerator.model.UserRole;

public class UserService {
	 
	public boolean authenticateAdminUser(String userMail, String password) {
		boolean result=false;
		User user = getUserByMail(userMail);
		if (user != null 
				&& user.getUserMail().equals(userMail) 
				&& user.getUserPassword().equals(password) 
				&& isAdminRole(user)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
	public boolean authenticateUser(String userMail, String password) {
		boolean result=false;
		User user = getUserByMail(userMail);
		if (user != null 
				&& user.getUserMail().equals(userMail) 
				&& user.getUserPassword().equals(password)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
	public boolean isAdminRole(User user){
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean result = false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from User u where u.idUser='" + user.getIdUser() + "' and u.userRole.userRoleName='admin'");
			User u = (User) query.uniqueResult();
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
	
	public User getUserById(String userId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from User where id_user='" + userId + "'");
			user = (User) query.uniqueResult();
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
			tx.begin();
			Query query = session.createQuery("from User where user_mail='" + userMail + "'");
			user = (User) query.uniqueResult();
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

	public List<User> getAllAdminUsers() {
		List<User> list = new ArrayList<User>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			list = (List<User>)session.createQuery("from User u where u.userRole.userRoleName='admin'").list();
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
	
	public List<User> getAllNotAdminUsers() {
		List<User> list = new ArrayList<User>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			list = (List<User>)session.createQuery("from User u where u.userRole.userRoleName='user'").list();
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
			Query query = session.createQuery("from User where id_user='" + user.getIdUser() + "'");
			User u = (User) query.uniqueResult();
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
	
	public boolean isUserExistsByMail(String userMail) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean result = false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("select u.userMail from User u where u.userMail='" + userMail + "'");
			String mail = (String) query.uniqueResult();
			tx.commit();
			if (userMail.equals(mail))
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
		LockService ls=new LockService();
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (!isUserExists(user))
			return false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from User where id_user='" + user.getIdUser() + "'");
			user = (User) query.uniqueResult();
			Set<UserLock> locks=user.getUserLocks();
			for(UserLock lock:locks){
				ls.deleteLock(lock);
			}
			session.delete(user);
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
