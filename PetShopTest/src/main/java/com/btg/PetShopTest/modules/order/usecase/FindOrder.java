package com.btg.PetShopTest.modules.order.usecase;

import com.btg.PetShopTest.modules.order.dto.OrderResponse;
import com.btg.PetShopTest.modules.order.entity.Order;
import com.btg.PetShopTest.modules.order.repository.usecase.OrderRepository;
import com.btg.PetShopTest.utils.OrderConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindOrder {
    private final OrderRepository orderRepository;

    public List<OrderResponse> findAll() {
        return OrderConvert.toResponseList(orderRepository.findAll());
    }

    public OrderResponse findById(String orderId) throws Exception {
        Order order = orderRepository.findOrderById(orderId);

        if (order == null) throw new Exception("Order not found");

        return OrderConvert.toResponseOrder(order);
    }
}

