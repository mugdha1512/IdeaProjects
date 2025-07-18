package dao;

import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addStudent(Student s) {
        jdbcTemplate.update("INSERT INTO students(name, age, course) VALUES (?, ?, ?)",
                s.getName(), s.getAge(), s.getCourse());
    }


    public List<Student> getAllStudents() {
        return jdbcTemplate.query("SELECT * FROM students", new BeanPropertyRowMapper<>(Student.class));
    }

    public void updateStudent(Student s) {
        jdbcTemplate.update("UPDATE students SET name=?, age=?, course=? WHERE id=?",
                s.getName(), s.getAge(), s.getCourse(), s.getId());
    }

    public void deleteStudent(int id) {
        jdbcTemplate.update("DELETE FROM students WHERE id=?", id);
    }
}
