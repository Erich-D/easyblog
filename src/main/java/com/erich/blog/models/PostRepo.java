/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erich.blog.models;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author etdeh
 */
public interface PostRepo extends JpaRepository<Post, Integer> {
    @Query("select p from posts p join p.tags t "
            + "where t.id > 49 AND (p.startDate = NULL OR p.startDate <= Date(NOW()))"
            + "AND (p.endDate = NULL OR p.endDate >= Date(NOW()))"
            + "AND p.appr = true "
            + "group by p.id")
    List<Post> findByRegulerTags();
    
    @Query("select p from posts p join p.tags t where t.name like 'aside'"
            + "AND (p.startDate = NULL OR p.startDate <= Date(NOW()))"
            + "AND (p.endDate = NULL OR p.endDate >= Date(NOW()))"
            + "AND p.appr = true")
    List<Post> findByAsideTag();
    
    List<Post> findByTopic(String topic);
}
