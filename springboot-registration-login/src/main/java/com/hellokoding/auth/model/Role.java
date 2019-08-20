package com.hellokoding.auth.model;

import javax.persistence.*;
import java.util.Set;

/*
* @Entity notation represent a table in our DB
* @Id represents our primary key
* @GeneratedValue with IDENTITY strategy indicates
* that the persistence provider must assign primary
* keys for the entity using DB's identity column. It
* also generates automatic values during the commit of
* any new object.
* @Transient fields don't participate in persistence
* and their values are never stored in the DB
* mappedBy indicates that the entity is the inverse of
* the relationship
*/

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
