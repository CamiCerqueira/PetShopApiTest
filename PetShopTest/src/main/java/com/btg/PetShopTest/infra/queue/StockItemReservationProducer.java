package com.btg.PetShopTest.infra.queue;

import com.btg.PetShopTest.infra.queue.DTO.StockReservationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockItemReservationProducer {
    public class ReservationItemStockProducer {
        //responsável por mandar a msg
        private final RabbitTemplate rabbitTemplate;

        private final Queue queue;
        //transforma um objeto em json e o processo inverso
        private final ObjectMapper objectMapper;

        public void enviar(StockReservationRequest StockReservation) throws JsonProcessingException {
            String message = objectMapper.writeValueAsString(StockReservation);
            rabbitTemplate.convertSendAndReceive(queue.getName(), message);
        }
}
