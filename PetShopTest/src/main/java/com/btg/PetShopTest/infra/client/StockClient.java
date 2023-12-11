package com.btg.PetShopTest.infra.client;

import com.btg.PetShopTest.modules.product.dto.ProductResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface StockClient {

    @GetExchange(value = "/query/{sku}")
    ProductResponse consultarEstoqueProduto(@PathVariable(value = "sku") String sku);
}
