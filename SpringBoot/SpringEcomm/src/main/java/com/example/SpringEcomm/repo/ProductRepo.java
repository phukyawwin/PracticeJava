package com.example.SpringEcomm.repo;

import com.example.SpringEcomm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
