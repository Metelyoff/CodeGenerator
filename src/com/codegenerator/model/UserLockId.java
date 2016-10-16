package com.codegenerator.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserLockId  implements java.io.Serializable {

	private static final long serialVersionUID = -7339678675604348793L;
	
	private int idLock;
    private int userIdUser;

    public UserLockId() {}

    public UserLockId(int idLock, int userIdUser) {
       this.idLock = idLock;
       this.userIdUser = userIdUser;
    }

    @Column(name="id_lock", nullable=false)
    public int getIdLock() {
        return this.idLock;
    }
    
    public void setIdLock(int idLock) {
        this.idLock = idLock;
    }

    @Column(name="user_id_user", nullable=false)
    public int getUserIdUser() {
        return this.userIdUser;
    }
    
    public void setUserIdUser(int userIdUser) {
        this.userIdUser = userIdUser;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserLockId) ) return false;
		 UserLockId castOther = ( UserLockId ) other; 
         
		 return (this.getIdLock()==castOther.getIdLock())
 && (this.getUserIdUser()==castOther.getUserIdUser());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdLock();
         result = 37 * result + this.getUserIdUser();
         return result;
   }
}


