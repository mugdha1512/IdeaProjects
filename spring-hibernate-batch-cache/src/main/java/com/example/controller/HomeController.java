package com.example.controller;

import com.example.entity.User;
import com.example.entity.UserList;
import com.example.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/users")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    // 🔹 Show Edit Form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user-form";  // Reuse form
    }

    // 🔹 Delete User
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }


    @GetMapping("/batch")
    public String showBatchForm(Model model) {
        UserList userList = new UserList();
        for (int i = 0; i < 5; i++) {
            userList.getUsers().add(new User());
        }
        model.addAttribute("userList", userList);
        return "batch-user-form";
    }

    @PostMapping("/save-batch")
    public String saveBatch(@ModelAttribute("userList") UserList userList) {
        for (User user : userList.getUsers()) {
            if (user.getName() != null && !user.getName().isBlank()
                    && user.getEmail() != null && !user.getEmail().isBlank()) {
                userService.save(user);
            }
        }
        return "redirect:/users";
    }

    @PostMapping("/users/deleteBatch")
    public String deleteSelectedUsers(@RequestParam("userIds") List<Long> userIds) {
        userService.deleteUsersInBatch(userIds);
        return "redirect:/users"; // redirect back to user list
    }
}
