package com.codegenerator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Hibernate;
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
			tx = session.beginTransaction();
			Query q=session.createQuery("from Lock as lock where lock.user.idUser='"+user.getIdUser()+"'");
			list = (List<Lock>)q.list();
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
	
	/*public boolean isLockExists(Lock lock) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean result = false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from Lock as lock where lock.id.idLock='" + lock.getId().getIdLock() + "'");
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
	}*/
	
	public boolean addLock(Lock lock,Integer idUser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (isLockExists(lock))
			return false;

		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.save(lock);
			User existingUser=(User)session. get(User.class, idUser);
			existingUser.getLocks().add(lock);
			session.save(existingUser);
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
	
	public boolean isLockExists(Lock lock) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean result = false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from Lock where id_lock='" + lock.getId().getIdLock() + "'");
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
