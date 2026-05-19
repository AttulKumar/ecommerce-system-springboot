package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;



    // GET all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }



    // ADD product
    public Product addProduct(Product product) {

        return productRepository.save(product);
    }

    public List<Product> searchProducts(String keyword) {

    return productRepository
            .findByNameContainingIgnoreCase(keyword);
    }

    public Product updateProduct(Long id,
                             Product updatedProduct) {

    Product product =
            productRepository.findById(id)
                    .orElseThrow();

    product.setName(updatedProduct.getName());
    product.setCategory(updatedProduct.getCategory());
    product.setPrice(updatedProduct.getPrice());
    product.setStock(updatedProduct.getStock());

    return productRepository.save(product);
    }

    public void deleteProduct(Long id) {

    productRepository.deleteById(id);
    }

}