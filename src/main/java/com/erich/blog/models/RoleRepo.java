/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erich.blog.models;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author etdeh
 */
public interface RoleRepo extends JpaRepository<Role, Integer> {
    
}
