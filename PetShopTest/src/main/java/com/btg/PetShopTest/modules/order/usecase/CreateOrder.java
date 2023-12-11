package com.btg.PetShopTest.modules.order.usecase;

import com.btg.PetShopTest.modules.customers.entity.Customer;
import com.btg.PetShopTest.modules.customers.repository.CustomerRepository;
import com.btg.PetShopTest.modules.order.dto.OrderRequest;
import com.btg.PetShopTest.modules.order.dto.OrderResponse;
import com.btg.PetShopTest.modules.order.entity.Order;
import com.btg.PetShopTest.modules.order.repository.usecase.OrderRepository;
import com.btg.PetShopTest.utils.OrderConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrder{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;

    public OrderResponse execute(OrderRequest OrderRequest) throws Exception {
        Customer customer = customerRepository.findByIdTransaction(OrderRequest.getCustomerId());

        if(customer == null) throw new Exception("Customer not found");

        Order order = OrderConvert.toEntity(customer);
        orderRepository.save(order);

        return OrderConvert.toResponseOrder(order);
    }
}
