package com.btg.PetShopTest.modules.orderItem.dto;

import com.btg.PetShopTest.modules.product.entity.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponse {
    private String orderId;
    private Product product;
    private Integer amount;
    private BigDecimal total;
}
