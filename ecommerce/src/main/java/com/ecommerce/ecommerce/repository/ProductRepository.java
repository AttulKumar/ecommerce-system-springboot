package com.ecommerce.ecommerce.repository;

import java.util.List;
import com.ecommerce.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByNameContainingIgnoreCase(String keyword);


}