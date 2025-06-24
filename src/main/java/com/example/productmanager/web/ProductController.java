package com.example.productmanager.web;

import com.example.productmanager.entities.Product;
import com.example.productmanager.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Liste des produits
    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    // Formulaire d'ajout
    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        return "productForm";
    }

    // Sauvegarder un produit
    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute Product product, BindingResult result) {
        if (result.hasErrors()) return "productForm";
        productRepository.save(product);
        return "redirect:/products";
    }

    // Supprimer un produit
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String listProducts(Model model,
                               @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        List<Product> products = productRepository.findByNameContains(keyword);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "productList";
    }

}
