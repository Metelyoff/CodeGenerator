package com.codegenerator.service;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.codegenerator.hibernate.util.HibernateUtil;
import com.codegenerator.model.User;
import com.codegenerator.model.Lock;

public class LockService {

	public Lock getLockById(String lockId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Lock lock = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from Lock where id_lock='" + lockId + "'");
			lock = (Lock) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lock;
	}
	
	public List<Lock> getAllLocks() {
		List<Lock> list = new ArrayList<Lock>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			list = (List<Lock>)session.createQuery("from Lock").list();
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
	
	public List<Lock> getLocksByUser(User user) {
		List<Lock> list = new ArrayList<Lock>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();//from Cat cat,Owner owner where cat.OwnerId = owner.Id and owner.Name='
			list = (List<Lock>)session.createQuery("select l from Lock as l inner join l.user as user where user.id_user='"+user.getId_user()+"'").list();
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
	 
	public void addLock(Lock lock) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.save(lock);
		/*User user = (User) session.get(User.class, userId);
		user.getLocks().add(lock);
		session.save(user);*/
	}
	
	public boolean isLockExists(Lock lock) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean result = false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from Lock where id_lock='" + lock.getId_lock() + "'");
			Lock l = (Lock) query.uniqueResult();
			tx.commit();
			if (l != null)
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
		
	
	public boolean deleteLock(Lock lock) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (!isLockExists(lock))
			return false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.delete(lock);
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
	
	public boolean editLock(Lock lock) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (!isLockExists(lock))
			return false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.update(lock);
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
