package com.codegenerator.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
public class LockId  implements java.io.Serializable {
	
     private int idLock;
     private int userIdUser;

    public LockId() {
    }

    public LockId(int idLock, int userIdUser) {
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
    
    public void setUserIdUser(Integer userIdUser) {
        this.userIdUser = userIdUser;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof LockId) ) return false;
		 LockId castOther = ( LockId ) other; 
         
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


