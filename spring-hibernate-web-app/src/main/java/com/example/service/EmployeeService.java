package com.example.service;

import com.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final SessionFactory sessionFactory;

    public EmployeeService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void createEmployee(String name, double salary) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = new Employee(name, salary);
        session.save(employee);
        System.out.println("Employee created successfully.");
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Employee", Employee.class).list();
    }

    @Transactional
    public void updateEmployee(Long id, String name, double salary) {
        Session session = sessionFactory.getCurrentSession();
        Employee emp = session.get(Employee.class, id);
        if (emp != null) {
            if (name != null && !name.trim().isEmpty()) emp.setName(name);
            if (salary > 0) emp.setSalary(salary);
            session.update(emp);
            System.out.println("Employee updated.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    @Transactional
    public void deleteEmployee(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Employee emp = session.get(Employee.class, id);
        if (emp != null) {
            session.delete(emp);
            System.out.println("Employee deleted.");
        } else {
            System.out.println("Employee not found.");
        }
    }
}
