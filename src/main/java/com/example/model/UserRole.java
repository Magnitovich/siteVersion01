package com.example.model;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_role")
public class UserRole implements GrantedAuthority{

    @Id
    @Column
    private Long id;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "name")
    private String role;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "userRoles")
    private List<UsersModel> usersModels;

    public List<UsersModel> getUsersModels() {
        return usersModels;
    }

    public void setUsersModels(List<UsersModel> usersModels) {
        this.usersModels = usersModels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String getAuthority() {
        return role;
    }
}
