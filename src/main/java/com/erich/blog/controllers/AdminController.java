/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erich.blog.controllers;

import com.erich.blog.models.Post;
import com.erich.blog.models.PostRepo;
import com.erich.blog.models.Tag;
import com.erich.blog.models.TagRepo;
import com.erich.blog.models.User;
import com.erich.blog.models.UserRepo;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    TagRepo tp;
    
    @GetMapping("/addEditPost")
    public String addEditPost(Model model, @RequestParam("blogPostId") Optional<String> blogPostId) {		
	setDefaultBlogPost(model);					
	return "admin/addEditPost";
    }
	

    @PostMapping("/addEditPost")
    public String addEditPostSubmit(Model model, Post blogPost, HttpServletRequest request) {
	System.out.println("Title is " + blogPost.getTopic());
	System.out.println("Body is " + blogPost.getBody());
        String[] tags = request.getParameter("tagString").split("#");
        if(!request.getParameter("start").isBlank()){
        blogPost.setStartDate(LocalDateTime.parse(request.getParameter("start"))); 
        }
        if(!request.getParameter("end").isBlank()){
        blogPost.setEndDate(LocalDateTime.parse(request.getParameter("end"))); 
        }
        for(String tag : tags){
            if(!tag.isBlank()){
                Tag t = tp.findByName(tag.trim());
                if(t == null){
                    t = new Tag();
                    t.setName(tag);
                    t = tp.save(t);
                    blogPost.getTags().add(t);
                    System.out.println(tag+" not found");
                }else{
                    blogPost.getTags().add(t);
                    System.out.println(tag+" found");
                }
            }
        }
        if(request.getParameter("aside") != null){
            blogPost.getTags().add(tp.findByName(request.getParameter("aside")));
        }
        blogPost.setAuthor(up.getById(1));
        blogPost.setAppr(true);
        rp.save(blogPost);
	System.out.println("Start Date is " + blogPost.getStartDate());
        System.out.println("End date is " + blogPost.getEndDate());
        System.out.println("Aside is " + blogPost.getTags());
        return "redirect:/";
    }	

	
    private void setDefaultBlogPost(Model model) {
	Post blogPost = new Post();
	model.addAttribute("blogPost", blogPost);
    }	
}
