package com.example.dao;

import com.example.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private static final Logger logger = LoggerFactory.getLogger(StudentDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Setter for injection via XML
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Student student) {
        logger.info("Saving student: {}", student.getName());
        String sql = "INSERT INTO students(name, email, dept_id) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getDeptId());
    }

    @Override
    public int update(Student student) {
        logger.info("Updating student with ID: {}", student.getId());
        String sql = "UPDATE students SET name = ?, email = ?, dept_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getDeptId(), student.getId());
    }

    @Override
    public int deleteById(int id) {
        logger.info("Deleting student with ID: {}", id);
        String sql = "DELETE FROM students WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Student findById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new StudentRowMapper());
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }

    @Override
    public List<Student> fetchStudentsWithDepartments() {
        String sql = "SELECT s.id, s.name, s.email, s.dept_id, d.dept_name " +
                "FROM students s JOIN department d ON s.dept_id = d.dept_id";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setEmail(rs.getString("email"));
            s.setDeptId(rs.getInt("dept_id"));
            s.setDeptName(rs.getString("dept_name"));
            return s;
        });
    }

    // RowMapper Inner Class
    private static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setEmail(rs.getString("email"));
            s.setDeptId(rs.getInt("dept_id"));
            return s;
        }
    }
}
