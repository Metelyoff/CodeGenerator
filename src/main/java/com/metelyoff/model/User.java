package com.metelyoff.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="user",catalog="codegenerator", uniqueConstraints = @UniqueConstraint(columnNames="user_mail") )
public class User  implements java.io.Serializable {

	private static final long serialVersionUID = 1712925643732361692L;
	
	private Integer idUser;
    private UserRole userRole;
    private String userMail;
    private String userPassword;
    private String userName;
    private Set<UserLock> userLocks = new HashSet<UserLock>(0);

    public User() {}

    public User(UserRole userRole, String userMail, String userPassword, String userName) {
        this.userRole = userRole;
        this.userMail = userMail;
        this.userPassword = userPassword;
        this.userName = userName;
    }
    
    public User(UserRole userRole, String userMail, String userPassword, String userName, Set<UserLock> userLocks) {
       this.userRole = userRole;
       this.userMail = userMail;
       this.userPassword = userPassword;
       this.userName = userName;
       this.userLocks = userLocks;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id_user", unique=true, nullable=false)
    public Integer getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_role_id_user_role", nullable=false)
    public UserRole getUserRole() {
        return this.userRole;
    }
    
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Column(name="user_mail", unique=true, nullable=false, length=45)
    public String getUserMail() {
        return this.userMail;
    }
    
    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    @Column(name="user_password", nullable=false, length=30)
    public String getUserPassword() {
        return this.userPassword;
    }
    
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Column(name="user_name", nullable=false, length=15)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<UserLock> getUserLocks() {
        return this.userLocks;
    }
    
    public void setUserLocks(Set<UserLock> userLocks) {
        this.userLocks = userLocks;
    }
}


