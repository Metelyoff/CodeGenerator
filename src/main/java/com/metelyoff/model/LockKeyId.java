package com.metelyoff.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LockKeyId  implements java.io.Serializable {

	private static final long serialVersionUID = -6614931502534675531L;
	
	private int idKey;
    private int lockIdLock;
    private int lockUserIdUser;

    public LockKeyId() {}

    public LockKeyId(int idKey, int lockIdLock, int lockUserIdUser) {
       this.idKey = idKey;
       this.lockIdLock = lockIdLock;
       this.lockUserIdUser = lockUserIdUser;
    }

    @Column(name="id_key", nullable=false)
    public int getIdKey() {
        return this.idKey;
    }
    
    public void setIdKey(int idKey) {
        this.idKey = idKey;
    }

    @Column(name="lock_id_lock", nullable=false)
    public int getLockIdLock() {
        return this.lockIdLock;
    }
    
    public void setLockIdLock(int lockIdLock) {
        this.lockIdLock = lockIdLock;
    }

    @Column(name="lock_user_id_user", nullable=false)
    public int getLockUserIdUser() {
        return this.lockUserIdUser;
    }
    
    public void setLockUserIdUser(int lockUserIdUser) {
        this.lockUserIdUser = lockUserIdUser;
    }

    public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof LockKeyId) ) return false;
		 LockKeyId castOther = ( LockKeyId ) other; 
         
		 return (this.getIdKey()==castOther.getIdKey())
 && (this.getLockIdLock()==castOther.getLockIdLock())
 && (this.getLockUserIdUser()==castOther.getLockUserIdUser());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdKey();
         result = 37 * result + this.getLockIdLock();
         result = 37 * result + this.getLockUserIdUser();
         return result;
   }
}


