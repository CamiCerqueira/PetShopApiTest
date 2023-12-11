package com.btg.PetShopTest.modules.product.usecases;

import com.btg.PetShopTest.modules.product.dto.ProductResponse;
import com.btg.PetShopTest.modules.product.repository.ProductRepository;
import com.btg.PetShopTest.utils.ProductConvert;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindProduct {
    @Autowired
    ProductRepository repository;

    public List<ProductResponse> listAll() {
        return ProductConvert.toListResponse(repository.findAll());
    }

    public ProductResponse findById(String id) {
        var product = repository.findProductById(id);

        if(product == null){
            throw new EntityNotFoundException("Product not found with ID: " + id);
        }

        return ProductConvert.toResponse(product);
    }
}