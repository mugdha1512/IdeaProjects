package com.example.studentapp.repository;

import com.example.studentapp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Student save(Student student) {
        if (student.getId() == null) {
            em.persist(student);
            return student;
        } else {
            return em.merge(student);
        }
    }

    @Override
    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    @Override
    public List<Student> findAll(int page, int size, String sortField, boolean asc) {
        String jpql = "SELECT s FROM Student s ORDER BY s." + sortField + (asc ? " ASC" : " DESC");
        TypedQuery<Student> query = em.createQuery(jpql, Student.class);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        Student student = findById(id);
        if (student != null) {
            em.remove(student);
        }
    }

    @Override
    public long count() {
        return em.createQuery("SELECT COUNT(s) FROM Student s", Long.class).getSingleResult();
    }
}
