package com.example.studentapp.batch;

import com.example.studentapp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.batch.item.ItemWriter;
import java.util.List;

public class StudentItemWriter implements ItemWriter<Student> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void write(List<? extends Student> items) {
        for (Student student : items) {
            em.persist(student);
        }
    }
}
