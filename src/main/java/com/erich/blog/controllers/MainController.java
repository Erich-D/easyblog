/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erich.blog.controllers;

import com.erich.blog.models.PostRepo;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author etdeh
 */
@Controller
@RequestMapping("/")
public class MainController {
    
   @Autowired
   PostRepo pr;
    
   @GetMapping("/")
   public String testPage(Model model) {
       model.addAttribute("posts", pr.findAll());
       return "index";
   }
   
   @PostMapping("/api/img")
   @ResponseBody
   public String testPost(HttpServletRequest request) throws IOException{
        System.out.println(request.getContentLength());
        StringBuilder builder = new StringBuilder();
        try (BufferedReader in = request.getReader()) {
            char[] buf = new char[4096];
            for (int len; (len = in.read(buf)) > 0; )
                builder.append(buf, 0, len);
        }
        String output = builder.toString();
        System.out.println(output);   
        return "redirect:/"; 
   }
   
   @PostMapping("/addEditPost")
	public String addEditPostSubmit(Model model) {
            /*
	    System.out.println("Title is " + blogPost.getTitle());
	    System.out.println("Body is " + blogPost.getBody());
	    
		return "admin/addEditPost";
	}

	
	private void setDefaultBlogPost(Model model) {
		BlogPost blogPost = new BlogPost();		
		model.addAttribute("blogPost", blogPost);
	}
        */
            
        return "index";}
}
