package com.btg.PetShopTest.modules.orderItem.repository;

import com.btg.PetShopTest.modules.orderItem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
    @Query(value = "SELECT * FROM order_item WHERE sku = :id", nativeQuery = true)
    OrderItem findOrderItemById(String id);
}
