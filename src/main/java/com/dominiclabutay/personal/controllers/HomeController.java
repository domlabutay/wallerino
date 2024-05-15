package com.dominiclabutay.personal.controllers;


import com.dominiclabutay.personal.model.Query;
import com.dominiclabutay.personal.services.UnsplashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class HomeController {
    @Autowired
    private UnsplashService unsplashService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("queryForm", new Query());
        return "index";
    }
    @PostMapping("/searched")
    public String searched(@ModelAttribute Query query, Model model) throws IOException, InterruptedException {
        System.out.println(query.toString());
        model.addAttribute("queryForm", new Query());
        String imageURL = unsplashService.fetchImageData(query.getSearchTerm());
        model.addAttribute("imageURL", imageURL);
        return "searched";
    }
}
