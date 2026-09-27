package com.example.saleappv1.controller;

import com.example.saleappv1.model.Category;
import com.example.saleappv1.model.Product;
import com.example.saleappv1.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Double fromPrice,
            @RequestParam(required = false) Double toPrice,
            Model model) {

        List<Product> filteredProducts = productService.filterProducts(categoryId, keyword, fromPrice, toPrice);

        model.addAttribute("products", filteredProducts);
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("keyword", keyword);
        model.addAttribute("fromPrice", fromPrice);
        model.addAttribute("toPrice", toPrice);
        model.addAttribute("totalProducts", filteredProducts.size());

        if (categoryId != null) {
            productService.getCategoryById(categoryId)
                    .ifPresent(cat -> model.addAttribute("selectedCategoryName", cat.getName()));
        }

        return "products";
    }

    @GetMapping("/products/{productId}")
    public String productDetail(@PathVariable int productId, Model model) {
        Optional<Product> productOpt = productService.getProductById(productId);

        if (productOpt.isEmpty()) {
            return "redirect:/products";
        }

        Product product = productOpt.get();
        model.addAttribute("product", product);
        model.addAttribute("categories", productService.getAllCategories());

        productService.getCategoryById(product.getCategoryId())
                .ifPresent(cat -> model.addAttribute("category", cat));

        List<Product> relatedProducts = productService.filterProducts(
                        product.getCategoryId(), null, null, null)
                .stream()
                .filter(p -> p.getId() != productId)
                .limit(4)
                .toList();
        model.addAttribute("relatedProducts", relatedProducts);

        return "product-detail";
    }
}
