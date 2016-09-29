package com.codegenerator.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Lock implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int id_lock;
	private String lock_name;
	private int lock_cells;
	private int lock_range;
	private String lock_key;
	
	@ManyToOne
    @JoinColumn(name="id_user")
	private User user;

	Lock() {}

	Lock(String name,int numberOfCells, int rangeOfValues, User user){
		this.lock_cells=numberOfCells;
		this.lock_range=rangeOfValues;
		this.lock_name=name;
		this.user=user;
	}
	
	public int getId_lock() {
		return id_lock;
	}

	public void setId_lock(int id_lock) {
		this.id_lock = id_lock;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLock_name() {
		return lock_name;
	}

	public void setLock_name(String lock_name) {
		this.lock_name = lock_name;
	}

	public int getLock_cells() {
		return lock_cells;
	}

	public void setLock_cells(int lock_cells) {
		this.lock_cells = lock_cells;
	}

	public int getLock_range() {
		return lock_range;
	}

	public void setLock_range(int lock_range) {
		this.lock_range = lock_range;
	}

	public String getLock_key() {
		return lock_key;
	}

	public void setLock_key(String lock_key) {
		this.lock_key = lock_key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_lock;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lock other = (Lock) obj;
		if (id_lock != other.id_lock)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	public String toString()
	{
		return "Lock name is "+lock_name+
		", number of cells="+lock_cells+
		", number of range="+lock_range+
		", key is "+lock_key;
	}
}
