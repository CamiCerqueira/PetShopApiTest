package com.btg.PetShopTest.infra.queue.DTO;

import lombok.Data;

@Data
public class StockReservationRequest {
    private String skuId;
    private Product item;
}
