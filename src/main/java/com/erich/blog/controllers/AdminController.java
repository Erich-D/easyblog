/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erich.blog.controllers;

import com.erich.blog.models.Post;
import com.erich.blog.models.PostRepo;
import com.erich.blog.models.UserRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author etdeh
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    PostRepo rp;
    @Autowired
    UserRepo up;
    
    @GetMapping("/addEditPost")
    public String addEditPost(Model model, @RequestParam("blogPostId") Optional<String> blogPostId) {		
	setDefaultBlogPost(model);					
	return "admin/addEditPost";
    }
	

    @PostMapping("/addEditPost")
    public String addEditPostSubmit(Model model, Post blogPost) {
	System.out.println("Title is " + blogPost.getTopic());
	System.out.println("Body is " + blogPost.getBody());
        blogPost.setAuthor(up.getById(1));
        rp.save(blogPost);
	    
        return "redirect:/";
    }	

	
    private void setDefaultBlogPost(Model model) {
	Post blogPost = new Post();
	model.addAttribute("blogPost", blogPost);
    }	
}
