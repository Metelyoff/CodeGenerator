package com.metelyoff.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lock_key",catalog="codegenerator")
public class LockKey  implements java.io.Serializable {

	private static final long serialVersionUID = -3253706996416499315L;
	
	private LockKeyId id;
    private UserLock userLock;
    private String keyCode;

    public LockKey() {}

    public LockKey(LockKeyId id, UserLock userLock) {
        this.id = id;
        this.userLock = userLock;
    }
    public LockKey(LockKeyId id, UserLock userLock, String keyCode) {
       this.id = id;
       this.userLock = userLock;
       this.keyCode = keyCode;
    }
   
    @EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="idKey", column=@Column(name="id_key", nullable=false) ), 
        @AttributeOverride(name="lockIdLock", column=@Column(name="lock_id_lock", nullable=false) ), 
        @AttributeOverride(name="lockUserIdUser", column=@Column(name="lock_user_id_user", nullable=false) ) } )
    public LockKeyId getId() {
        return this.id;
    }
    
    public void setId(LockKeyId id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( { 
        @JoinColumn(name="lock_id_lock", referencedColumnName="id_lock", nullable=false, insertable=false, updatable=false), 
        @JoinColumn(name="lock_user_id_user", referencedColumnName="user_id_user", nullable=false, insertable=false, updatable=false) } )
    public UserLock getUserLock() {
        return this.userLock;
    }
    
    public void setUserLock(UserLock userLock) {
        this.userLock = userLock;
    }
    
    @Column(name="key_code", length=10)
    public String getKeyCode() {
        return this.keyCode;
    }
    
    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }
}


