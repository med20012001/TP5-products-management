package com.example.productmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Le nom est obligatoire")
    private String name;

    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit être positif")
    private double price;

    @Min(value = 0, message = "La quantité doit être positive")
    private int quantity;
}
