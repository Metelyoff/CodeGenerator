package com.codegenerator.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
/*@IdClass(LockId.class)*/
@Table(name="lock",catalog="codegenerator")
public class Lock  implements java.io.Serializable {

	/*@Id @GeneratedValue()
	private Integer id_lock;*/
    private LockId id;
    private User user;
    private String lockName;
    private int lockCells;
    private int lockRange;
    private String lockKey;

    public Lock() {}

    public Lock(String lockName, int lockCells, int lockRange) {
        this.lockName = lockName;
        this.lockCells = lockCells;
        this.lockRange = lockRange;
    }
    
    public Lock(User user, String lockName, int lockCells, int lockRange) {
        this.user = user;
        this.lockName = lockName;
        this.lockCells = lockCells;
        this.lockRange = lockRange;
    }
    
    public Lock(User user, String lockName, int lockCells, int lockRange, String lockKey) {
        this.user = user;
        this.lockName = lockName;
        this.lockCells = lockCells;
        this.lockRange = lockRange;
        this.lockKey = lockKey;
    }
    
    public Lock(LockId id, User user, String lockName, int lockCells, int lockRange, String lockKey) {
       this.id = id;
       this.user = user;
       this.lockName = lockName;
       this.lockCells = lockCells;
       this.lockRange = lockRange;
       this.lockKey = lockKey;
    }
    
    public Lock(LockId id, User user, String lockName, int lockCells, int lockRange) {
        this.id = id;
        this.user = user;
        this.lockName = lockName;
        this.lockCells = lockCells;
        this.lockRange = lockRange;
     }   
    
    @EmbeddedId 
    @AttributeOverrides( {
        @AttributeOverride(name="idLock", column=@Column(name="id_lock", nullable=false)), 
        @AttributeOverride(name="userIdUser", column=@Column(name="user_id_user", nullable=false) ) } )
    public LockId getId() {
        return this.id;
    }
    
    public void setId(LockId id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id_user", nullable=false, insertable=false, updatable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    @Column(name="lock_name", nullable=false, length=15)
    public String getLockName() {
        return this.lockName;
    }
    
    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    @Column(name="lock_cells", nullable=false)
    public int getLockCells() {
        return this.lockCells;
    }
    
    public void setLockCells(int lockCells) {
        this.lockCells = lockCells;
    }

    @Column(name="lock_range", nullable=false)
    public int getLockRange() {
        return this.lockRange;
    }
    
    public void setLockRange(int lockRange) {
        this.lockRange = lockRange;
    }

    @Column(name="lock_key", length=10)
    public String getLockKey() {
        return this.lockKey;
    }
    
    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }
}


