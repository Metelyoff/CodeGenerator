package com.metelyoff.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_role",catalog="metelyoff")
public class UserRole  implements java.io.Serializable {

	private static final long serialVersionUID = 4599385113053362380L;
	
	private Integer idUserRole;
    private String userRoleName;
    private Set<User> users = new HashSet<User>(0);

    public UserRole() {}

    public UserRole(String userRoleName) {
        this.userRoleName = userRoleName;
    }
    public UserRole(String userRoleName, Set<User> users) {
       this.userRoleName = userRoleName;
       this.users = users;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id_user_role", unique=true, nullable=false)
    public Integer getIdUserRole() {
        return this.idUserRole;
    }
    
    public void setIdUserRole(Integer idUserRole) {
        this.idUserRole = idUserRole;
    }

    @Column(name="user_role_name", nullable=false, length=45)
    public String getUserRoleName() {
        return this.userRoleName;
    }
    
    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="userRole")
    public Set<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }
}


