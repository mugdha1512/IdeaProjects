package com.example.repository;

import com.example.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {
    Sport findByName(String name);

    @Query("SELECT s FROM Sport s WHERE s.name LIKE %:name%")
    Sport findByNameContaining(@Param("name") String name);

    // Alternative
//    @Query(value = "SELECT * FROM sport WHERE name = :name", nativeQuery = true)
//    Sport findByName(@Param("name") String name);
//
//    @Query(value = "SELECT * FROM sport WHERE name LIKE CONCAT('%', :name, '%')", nativeQuery = true)
//    Sport findByNameContaining(@Param("name") String name);
}