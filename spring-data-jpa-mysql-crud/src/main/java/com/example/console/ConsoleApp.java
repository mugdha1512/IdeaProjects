package com.example.console;
import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class ConsoleApp {
    public static void main(String[] args) {
// Initialize Spring Context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                "com.example.config");
        ProductRepository repository = context.getBean(ProductRepository.class);
// CRUD Operations
// Create
        Product product1 = new Product("Laptop", 999.99, "Electronics");
        Product product2 = new Product("Smartphone", 599.99, "Electronics");
        Product product3 = new Product("Desk Chair", 199.99, "Furniture");
        Product product4 = new Product("Headphones", 79.99, "Electronics");
        repository.save(product1);
        repository.save(product2);
        repository.save(product3);
        repository.save(product4);
// Read - Basic Operations
        System.out.println("\nAll Products:");
        List<Product> allProducts = repository.findAll();
        allProducts.forEach(System.out::println);
        System.out.println("\nFind by ID (1):");
        Optional<Product> product = repository.findById(1L);
        product.ifPresent(System.out::println);
// Update
        product.ifPresent(p -> {
            p.setPrice(1099.99);
            repository.save(p);
            System.out.println("\nUpdated Product:");
            System.out.println(p);
        });
// Query Methods
        System.out.println("\nElectronics Products:");
        List<Product> electronics = repository.findByCategory("Electronics");
        electronics.forEach(System.out::println);
        System.out.println("\nProducts with price > 500:");
        List<Product> expensiveProducts = repository.findByPriceGreaterThan(500.0);
        expensiveProducts.forEach(System.out::println);
        System.out.println("\nProducts containing 'phone':");
        List<Product> phoneProducts = repository.findByNameContainingIgnoreCase("phone");
        phoneProducts.forEach(System.out::println);
// Pagination and Sorting
        System.out.println("\nPaginated Electronics Products (Page 0, Size 2, Sorted by price):");
        Pageable pageable = PageRequest.of(0, 2, Sort.by("price").ascending());
        Page<Product> electronicsPage = repository.findByCategory("Electronics", pageable);
        electronicsPage.forEach(System.out::println);
        System.out.println("\nProducts with price between 50 and 1000 (Page 0, Size 2, Sorted by name):");
        Pageable pageable2 = PageRequest.of(0, 2, Sort.by("name").ascending());
        Page<Product> priceRangePage = repository.findByPriceBetween(50.0, 1000.0, pageable2);
        priceRangePage.forEach(System.out::println);
// Delete
        System.out.println("\nDeleting product with ID 3");
        repository.deleteById(3L);
        System.out.println("\nAll Products after deletion:");
        repository.findAll().forEach(System.out::println);
// Close context
        context.close();
    }
}