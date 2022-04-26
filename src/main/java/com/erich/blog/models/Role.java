/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erich.blog.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author etdeh
 */
@Entity(name = "roles")
public class Role {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> posts = new ArrayList<User>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getPosts() {
        return posts;
    }

    public void setPosts(List<User> posts) {
        this.posts = posts;
    }
    
    
}
