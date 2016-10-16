package com.metelyoff.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.metelyoff.hibernate.util.HibernateUtil;
import com.metelyoff.model.User;
import com.metelyoff.model.LockKey;
import com.metelyoff.model.UserLock;

public class LockService {
	
	public UserLock getLockById(String lockId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		UserLock lock = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from UserLock where id_lock='" + lockId + "'");
			lock = (UserLock) query.uniqueResult();
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
	
	public List<UserLock> getAllLocks() {
		List<UserLock> list = new ArrayList<UserLock>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			list = (List<UserLock>)session.createQuery("from UserLock").list();
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
	
	public List<UserLock> getLocksByUser(User user) {
		List<UserLock> list = new ArrayList<UserLock>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q=session.createQuery("from UserLock as lock where lock.user.idUser='"+user.getIdUser()+"'");
			list = (List<UserLock>)q.list();
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
	
	/*public List<UserLock> getLocksByUser(String userId) {
		List<UserLock> list = new ArrayList<UserLock>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q=session.createQuery("from UserLock as lock where lock.user.idUser='"+userId+"'");
			list = (List<UserLock>)q.list();
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
	}*/
	
	public boolean addLock(UserLock lock,Integer idUser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (isLockExists(lock))
			return false;

		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.save(lock);
			User existingUser=(User)session. get(User.class, idUser);
			existingUser.getUserLocks().add(lock);
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
	
	public boolean isLockExists(UserLock lock) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean result = false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from UserLock where id_lock='" + lock.getId().getIdLock() + "'");
			UserLock l = (UserLock) query.uniqueResult();
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

	public boolean deleteLock(UserLock lock) {
		KeyService ks=new KeyService();
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (!isLockExists(lock))
			return false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from UserLock where id_lock='" + lock.getId().getIdLock() + "'");
			lock = (UserLock) query.uniqueResult();
			Set<LockKey> keys=lock.getLockKeys();
			for(LockKey key:keys){
				ks.deleteKeyByLock(key,lock);
			}
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
	
	public boolean editLock(UserLock lock) {
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
