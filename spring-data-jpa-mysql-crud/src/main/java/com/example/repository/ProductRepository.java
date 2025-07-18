package com.example.repository;
import com.example.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Query Methods
    List<Product> findByCategory(String category);
    List<Product> findByPriceGreaterThan(Double price);
    List<Product> findByNameContainingIgnoreCase(String name);
    // Query Methods with Pagination and Sorting
    Page<Product> findByCategory(String category, Pageable pageable);
    Page<Product> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);
}