package controller;

import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.StudentService;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    public void add(Student s) { studentService.addStudent(s); }
    public List<Student> viewAll() { return studentService.getAllStudents(); }
    public void update(Student s) { studentService.updateStudent(s); }
    public void delete(int id) { studentService.deleteStudent(id); }
}
