package com.shopsync.controller;

import com.shopsync.service.ProductService;
import com.shopsync.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String showProducts(Model model) {
        List<Product> products = productService.getProducts("", 0);
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/list")
    public String list(@RequestParam String search, Model model) {
        model.addAttribute("products", productService.getProducts(search, 0));
        return "fragments/product-list :: productList";
    }

    @PostMapping("/admin/import")
    public String importProducts(@RequestParam("file") MultipartFile file) {
        // Parse CSV and call batchImport
        return "redirect:/products";
    }
}
