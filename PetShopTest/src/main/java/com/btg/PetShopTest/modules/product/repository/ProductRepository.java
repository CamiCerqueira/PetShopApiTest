package com.btg.PetShopTest.modules.product.repository;

import com.btg.PetShopTest.modules.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(value = "SELECT * FROM products WHERE sku = :id", nativeQuery = true)
    Product findProductById(String id);
}