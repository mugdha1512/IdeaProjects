package com.example.controller;

import com.example.entity.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")  // 🎯 Base URL for all endpoints under /employees
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // 🔹 List all employees: /employees
    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employeeList = employeeService.getAllEmployees();
        model.addAttribute("employees", employeeList);
        return "employee-list";  // JSP: /WEB-INF/jsp/employee-list.jsp
    }

    // 🔹 Show create form: /employees/create
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";  // JSP: /WEB-INF/jsp/employee-form.jsp
    }

    // 🔹 Handle form submit (create): /employees/save
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.createEmployee(employee.getName(), employee.getSalary());
        return "redirect:/employees";
    }

    // 🔹 Show edit form: /employees/edit/{id}
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    // 🔹 Handle form submit (update): /employees/update
    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.updateEmployee(employee.getId(), employee.getName(), employee.getSalary());
        return "redirect:/employees";
    }

    // 🔹 Delete employee: /employees/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}
