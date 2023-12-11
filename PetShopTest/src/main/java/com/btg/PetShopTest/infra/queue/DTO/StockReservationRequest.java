package com.btg.PetShopTest.infra.queue.DTO;

import com.btg.PetShopTest.modules.product.entity.Product;
import lombok.Data;

@Data
public class StockReservationRequest {
    private String skuId;
    private Product item;
}
