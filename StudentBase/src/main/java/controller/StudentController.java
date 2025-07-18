package controller;

import java.util.List;
import dao.StudentDAO;
import model.Student;

public class StudentController {
    private StudentDAO dao = new StudentDAO();

    public void addStudent(Student student) {
        dao.addStudent(student);
    }

    public List<Student> getAllStudents() {
        return dao.getAllStudents();
    }

    public void updateStudent(Student student) {
        dao.updateStudent(student);
    }

    public void deleteStudent(int id) {
        dao.deleteStudent(id);
    }
}
