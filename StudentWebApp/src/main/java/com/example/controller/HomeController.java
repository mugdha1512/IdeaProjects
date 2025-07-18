package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/"})
    public String showHome(Model model) {
        model.addAttribute("message", "Welcome to the Student Web Application!");
        return "index"; // maps to /WEB-INF/views/index.html
    }
}
