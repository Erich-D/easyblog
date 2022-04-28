/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erich.blog.controllers;

import com.erich.blog.models.Post;
import com.erich.blog.models.PostRepo;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
   public String testPage(Model model, String topic) {
       System.out.println(topic);
       List<Post> posts;
       if(topic == null){
           posts = pr.findByRegulerTags();
       }else{
           posts = pr.findByTopic(topic);
       }
       List<Post> asides = pr.findByAsideTag();
       List<String> topics = pr.findAll().stream()
               .map(t -> t.getTopic())
               .limit(5)
               .collect(Collectors.toList());
       model.addAttribute("topics", topics);
       model.addAttribute("asides", asides);
       model.addAttribute("posts", posts);
       return "index";
   }
   
   
}
