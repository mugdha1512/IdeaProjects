package com.example.controller;
import com.example.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @Autowired
    private GreetingService greetingService;


    @GetMapping("/")
    public String defaultPage() {
        return "redirect:/greet";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam(name = "name", defaultValue = "World") String name,
                        Model model) {
        model.addAttribute("name", greetingService.getGreeting(name));
        return "greet"; // mapped to greet.html
    }
}