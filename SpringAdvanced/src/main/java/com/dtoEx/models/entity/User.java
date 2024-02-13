package com.dtoEx.models.entity;


import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String email;
    private String password;
    private String fullName;
    private Set<Game> games;
    private Boolean isAdmin;
@Column
    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Column
    public String getEmail() {
        return email;
    }

    @Column
    public String getPassword() {
        return password;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    @Column
    @ManyToMany
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
