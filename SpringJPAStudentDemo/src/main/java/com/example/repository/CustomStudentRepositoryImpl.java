package com.example.repository;

import com.example.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomStudentRepositoryImpl implements CustomStudentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findStudentsByTradeAndSport(String tradeName, String sportName) {
        String jpql = "SELECT s FROM Student s JOIN s.trade t JOIN s.sports sp WHERE t.name = :tradeName AND sp.name = :sportName";
        TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);
        query.setParameter("tradeName", tradeName);
        query.setParameter("sportName", sportName);
        return query.getResultList();
    }
}