package com.shopsync.service;

import com.shopsync.model.Product;

import java.util.List;

public interface ProductService {
    void batchImport(List<Product> products);
    List<Product> getProducts(String search, int page);
}
