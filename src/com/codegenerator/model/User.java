package com.codegenerator.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="user",catalog="codegenerator",uniqueConstraints = @UniqueConstraint(columnNames="user_mail") )
public class User  implements java.io.Serializable {

     private Integer idUser;
     private String userMail;
     private String userRole;
     private String userPassword;
     private String userName;
     private Set<Lock> locks = new HashSet<Lock>(0);
     /*private List<Lock> locks = new ArrayList<Lock>(0);*/
     
    public User() {}

    public User(String userMail, String userPassword, String userName) {
        this.userMail = userMail;
        this.userPassword = userPassword;
        this.userName = userName;
    }
    
    public User(String userMail, String userRole, String userPassword, String userName, Set<Lock> locks) {
       this.userMail = userMail;
       this.userRole = userRole;
       this.userPassword = userPassword;
       this.userName = userName;
       this.locks = locks;
    }
    
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id_user", unique=true, nullable=false)
    public Integer getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Column(name="user_mail", unique=true, nullable=false, length=45)
    public String getUserMail() {
        return this.userMail;
    }
    
    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    @Column(name="user_role", length=5)
    public String getUserRole() {
        return this.userRole;
    }
    
    public void setUserRole(String userRole) {
        this.userRole = userRole;
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
    public Set<Lock> getLocks() {
        return this.locks;
    }
    
    public void setLocks(Set<Lock> locks) {
        this.locks = locks;
    }
}


