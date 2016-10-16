package com.codegenerator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.codegenerator.hibernate.util.HibernateUtil;
import com.codegenerator.model.LockKey;
import com.codegenerator.model.UserLock;

public class KeyService {

	public LockKey getKeyById(String keyId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		LockKey key = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from LockKey where id_key='" + keyId + "'");
			key = (LockKey) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return key;
	}
	
	public List<LockKey> getAllKeysByLock(UserLock lock) {
		List<LockKey> list = new ArrayList<LockKey>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			list = (List<LockKey>)session.createQuery("from LockKey as k where k.userLock.id.idLock='"+lock.getId().getIdLock()+"'").list();
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
	
	public boolean addKeyByLockId(LockKey key,UserLock lockId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (isKeyExistsInLock(key,lockId))
			return false;

		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.save(key);
			Query query = session.createQuery("from UserLock where id_lock='" + lockId.getId().getIdLock()+ "'");
			UserLock existingLock= (UserLock) query.uniqueResult();
			existingLock.getLockKeys().add(key);
			session.save(existingLock);
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
	
	public boolean isKeyExistsInLock(LockKey key,UserLock lock) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean result = false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from LockKey as k where k.userLock.id.idLock='"+lock.getId().getIdLock()+"'");
			LockKey k = (LockKey) query.uniqueResult();
			tx.commit();
			if (k != null)
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
	
	public boolean editKey(LockKey key,UserLock lock) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (!isKeyExistsInLock(key,lock))
			return false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.update(key);
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
	
	public boolean deleteKeyByLock(LockKey key,UserLock lock) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (!isKeyExistsInLock(key,lock))
			return false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();			
			session.delete(key);
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
	
	public boolean deleteAllKeysByLockId(LockKey key,UserLock lock) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (!isKeyExistsInLock(key,lock))
			return false;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();			
			Query query = session.createQuery("from LockKey as k where k.userLock.id.idLock='"+lock.getId().getIdLock()+"'");
			lock = (UserLock) query.uniqueResult();
			Set<LockKey> keys=lock.getLockKeys();
			for(LockKey k:keys){
				deleteKeyByLock(k,lock);
			}
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
