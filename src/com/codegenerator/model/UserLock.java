package com.codegenerator.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_lock",catalog="codegenerator")
public class UserLock  implements java.io.Serializable {

	private static final long serialVersionUID = 3861996699522142015L;
	
	private UserLockId id;
    private User user;
    private String lockName;
    private int lockCells;
    private int lockRange;
    private String lockKey;
    private Set<LockKey> lockKeys = new HashSet<LockKey>(0);

    public UserLock() {}

    public UserLock(UserLockId id, User user, String lockName, int lockCells, int lockRange) {
        this.id = id;
        this.user = user;
        this.lockName = lockName;
        this.lockCells = lockCells;
        this.lockRange = lockRange;
    }
    
    public UserLock(UserLockId id, User user, String lockName, int lockCells, int lockRange, String lockKey, Set<LockKey> lockKeys) {
       this.id = id;
       this.user = user;
       this.lockName = lockName;
       this.lockCells = lockCells;
       this.lockRange = lockRange;
       this.lockKey = lockKey;
       this.lockKeys = lockKeys;
    }
   
    @EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="idLock", column=@Column(name="id_lock", nullable=false) ), 
        @AttributeOverride(name="userIdUser", column=@Column(name="user_id_user", nullable=false) ) } )
    public UserLockId getId() {
        return this.id;
    }
    
    public void setId(UserLockId id) {
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

    @OneToMany(fetch=FetchType.LAZY, mappedBy="userLock")
    public Set<LockKey> getLockKeys() {
        return this.lockKeys;
    }
    
    public void setLockKeys(Set<LockKey> lockKeys) {
        this.lockKeys = lockKeys;
    }
}


