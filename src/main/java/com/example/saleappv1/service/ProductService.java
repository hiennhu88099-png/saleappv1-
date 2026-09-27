package com.example.saleappv1.service;

import com.example.saleappv1.model.Category;
import com.example.saleappv1.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        loadProducts();
        loadCategories();
    }

    private void loadProducts() {
        try {
            ClassPathResource resource = new ClassPathResource("data/products.json");
            products = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<Product>>() {}
            );
        } catch (IOException e) {
            products = new ArrayList<>();
        }
    }

    private void loadCategories() {
        try {
            ClassPathResource resource = new ClassPathResource("data/categories.json");
            categories = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<Category>>() {}
            );
        } catch (IOException e) {
            categories = new ArrayList<>();
        }
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public List<Category> getAllCategories() {
        return categories;
    }

    public Optional<Product> getProductById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public Optional<Category> getCategoryById(int id) {
        return categories.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    public List<Product> filterProducts(Integer categoryId, String keyword,
                                        Double fromPrice, Double toPrice) {
        return products.stream()
                .filter(p -> categoryId == null || p.getCategoryId() == categoryId)
                .filter(p -> keyword == null || keyword.isBlank() ||
                        p.getName().toLowerCase().contains(keyword.trim().toLowerCase()))
                .filter(p -> fromPrice == null || p.getPrice() >= fromPrice)
                .filter(p -> toPrice == null || p.getPrice() <= toPrice)
                .collect(Collectors.toList());
    }
}
