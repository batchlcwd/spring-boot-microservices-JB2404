package com.substring.foodie.payment.functions;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class OrderNotification {

    @Bean
    public Consumer<Message<OrderDto>> orderEvents() {
        return message -> {
            OrderDto orderDto = message.getPayload();
            System.out.println("Order Event received");
            System.out.println("delivery process started ");
            System.out.println("order id " + orderDto.orderId());
            System.out.println("order status " + orderDto.status());
            System.out.println("order amount " + orderDto.amount());
            System.out.println("_____fine_____");

//            publish the event:
//            order mil chuka hai aur hame delivery process kar di hai.
            sendAcknowlegement();
        };


    }

    public void sendAcknowlegement() {
        //using stream bridge to send the event with message.
    }

}
