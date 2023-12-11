package com.btg.PetShopTest.utils;

import com.btg.PetShopTest.modules.customers.entity.Customer;
import com.btg.PetShopTest.modules.order.dto.OrderResponse;
import com.btg.PetShopTest.modules.order.entity.Order;
import com.btg.PetShopTest.modules.orderItem.dto.OrderItemResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderConvert {
    public static Order toEntity(Customer customer){
        Order order = new Order();

        order.setIdTransaction(UUID.randomUUID().toString());
        order.setCustomer(customer);
        order.setStatus(Order.OrderStatus.OPEN);
        order.setOrderItens(new ArrayList<>());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        order.setTotal(BigDecimal.ZERO);
        return order;
    }

    public static OrderResponse toResponseOrder(Order order){
        OrderResponse orderResponse = new OrderResponse();
        List<OrderItemResponse> orderItemsResponse = OrderItemConvert.toResponseList(order.getOrderItens());

        orderResponse.setIdTransaction(order.getIdTransaction());
        orderResponse.setCustomer(order.getCustomer().getIdTransaction());
        orderResponse.setItems(orderItemsResponse);
        orderResponse.setStatus(order.getStatus());
        orderResponse.setTotal(order.getTotal());

        return orderResponse;
    }

    public static List<OrderResponse> toResponseList(List<Order> orders){
        return orders.stream()
                .map(OrderConvert::toResponseOrder)
                .collect(Collectors.toList());
    }
}
