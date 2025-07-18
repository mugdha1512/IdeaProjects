package com.example.controller;

import com.example.dao.StudentDAO;
import com.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentDAO studentDAO;

    // Show all students
    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentDAO.findAll();
        model.addAttribute("students", students);
        return "student-list"; // maps to student-list.html
    }

    // Show form to add new student
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form"; // student-form.html
    }

    // Save student (new or updated)
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        if (student.getId() == 0) {
            studentDAO.save(student);
        } else {
            studentDAO.update(student);
        }
        return "redirect:/students";
    }

    // Show form to update a student
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        Student student = studentDAO.findById(id);
        model.addAttribute("student", student);
        return "student-form";
    }

    // Delete a student
    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("id") int id) {
        studentDAO.deleteById(id);
        return "redirect:/students";
    }
}
