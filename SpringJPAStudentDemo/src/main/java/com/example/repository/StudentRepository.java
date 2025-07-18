package com.example.repository;

import com.example.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, CustomStudentRepository {
    // Query Methods
    List<Student> findByTradeId(Long tradeId);
    List<Student> findByLastNameContainingIgnoreCase(String lastName);

    // Custom JPQL Query
    @Query("SELECT s FROM Student s WHERE SIZE(s.sports) > ?1")
    List<Student> findStudentsWithSportsGreaterThan(int count);

    // Pagination and Sorting
    Page<Student> findByTradeId(Long tradeId, Pageable pageable);
}