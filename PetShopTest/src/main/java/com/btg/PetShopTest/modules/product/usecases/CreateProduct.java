package com.btg.PetShopTest.modules.product.usecases;

import com.btg.PetShopTest.modules.product.dto.ProductRequest;
import com.btg.PetShopTest.modules.product.dto.ProductResponse;
import com.btg.PetShopTest.modules.product.entity.Product;
import com.btg.PetShopTest.modules.product.repository.ProductRepository;
import com.btg.PetShopTest.utils.ProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProduct {
    @Autowired
    ProductRepository repository;

    public ProductResponse execute(ProductRequest productRequest) throws Exception {
        validateProduct(productRequest);

        Product product = ProductConvert.toEntity(productRequest);
        repository.save(product);
        return ProductConvert.toResponse(product);
    }

    private void validateProduct(ProductRequest productRequest) throws Exception {
        if (productRequest.getName() == null) {
            throw new Exception("Name is required");
        }

        if (productRequest.getQuantityStock() == null) {
            throw new Exception("Quantity is required");
        }

        if (productRequest.getPrice() == null) {
            throw new Exception("Price is required");
        }
    }
}