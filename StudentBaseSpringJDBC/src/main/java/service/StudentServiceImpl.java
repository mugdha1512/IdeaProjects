package service;

import dao.StudentDAO;
import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDAO studentDAO;

    public void addStudent(Student s) { studentDAO.addStudent(s); }
    public List<Student> getAllStudents() { return studentDAO.getAllStudents(); }
    public void updateStudent(Student s) { studentDAO.updateStudent(s); }
    public void deleteStudent(int id) { studentDAO.deleteStudent(id); }
}
