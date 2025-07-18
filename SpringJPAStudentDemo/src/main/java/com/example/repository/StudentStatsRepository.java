package com.example.repository;

import com.example.entity.Student;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryDefinition(domainClass = Student.class, idClass = Long.class)
public interface StudentStatsRepository extends org.springframework.data.repository.Repository<Student, Long> {
    Optional<Student> findById(Long id);
    List<Student> findByEmailContainingIgnoreCase(String email);
}