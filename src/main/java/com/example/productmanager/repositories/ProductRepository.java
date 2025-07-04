package com.example.productmanager.repositories;

import com.example.productmanager.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContains(String keyword);

}

