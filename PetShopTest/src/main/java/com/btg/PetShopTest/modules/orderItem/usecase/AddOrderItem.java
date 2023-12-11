package com.btg.PetShopTest.modules.orderItem.usecase;

import com.btg.PetShopTest.infra.exception.ClientBadRequest;
import com.btg.PetShopTest.infra.queue.DTO.StockReservationRequest;
import com.btg.PetShopTest.infra.queue.DTO.StockItemReservationProducer;
import com.btg.PetShopTest.modules.order.entity.Order;
import com.btg.PetShopTest.modules.order.repository.usecase.OrderRepository;
import com.btg.PetShopTest.modules.order.usecase.UpdateOrder;
import com.btg.PetShopTest.modules.order.utils.CalculateTotal;
import com.btg.PetShopTest.modules.orderItem.dto.OrderItemRequest;
import com.btg.PetShopTest.modules.orderItem.dto.OrderItemResponse;
import com.btg.PetShopTest.modules.orderItem.entity.OrderItem;
import com.btg.PetShopTest.modules.orderItem.repository.OrderItemRepository;
import com.btg.PetShopTest.modules.product.entity.Product;
import com.btg.PetShopTest.modules.product.repository.ProductRepository;
import com.btg.PetShopTest.utils.OrderItemConvert;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AddOrderItem {
        @Autowired
        OrderRepository orderRepository;
        @Autowired
        ProductRepository productRepository;
        @Autowired
        OrderItemRepository orderItemRepository;
        @Autowired
        UpdateOrder updateOrder;
        @Autowired
        StockItemReservationProducer StockItemReservation;

        public OrderItemResponse execute(String orderId, OrderItemRequest orderItemRequest) throws Exception {
            Order order = validateOrder(orderId);

            Product product = validateProduct(orderItemRequest.getProductId());

            OrderItem orderItem = saveOrderItem(order, orderItemRequest, product);

            reservarItems(orderId, product);

            updateOrderTotal(order);
            updateOrder.execute(orderId, order);

            return OrderItemConvert.toResponseOrderItem(orderItem);
        }

        private Order validateOrder(String orderID) throws ClientBadRequest {
            Order order = orderRepository.findOrderById(orderID);
            if (order == null) {
                throw new ClientBadRequest("Order not found");
            }
            return order;
        }

        private Product validateProduct(String productSku) throws Exception {
            Product product = productRepository.findProductById(productSku);
            if (product == null) {
                throw new Exception("Product not found");
            }
            return product;
        }


        private void reservarItems(String skuId, Product item) {
            StockReservationRequest reservarEstoqueRequest = new StockReservationRequest();
            reservarEstoqueRequest.setSkuId(skuId);
            reservarEstoqueRequest.setItem(item);

            try {
                StockItemReservation.send(reservarEstoqueRequest);
            } catch (JsonProcessingException e) {
                log.error("Não foi possível enviar a mensagem ao destinatário", e);
                throw new RuntimeException(e);
            }
        }

        private OrderItem saveOrderItem(Order order, OrderItemRequest orderItemRequest, Product product) {
            OrderItem newItem = OrderItemConvert.toEntity(orderItemRequest, order, product);
            orderItemRepository.save(newItem);
            order.getOrderItens().add(newItem);
            return newItem;
        }

        private void updateOrderTotal(Order order) {
            order.setTotal(CalculateTotal.execute(order));
            orderRepository.save(order);
        }
}
