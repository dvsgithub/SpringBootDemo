package com.ma.SpringBootDemo;

import org.springframework.stereotype.Component;

@Component
public class OrderProcessor {

    private final OrderRepository repository;
    private final ConfirmationSender confirmationSender;

    public OrderProcessor(OrderRepository repository, ConfirmationSender confirmationSender) {
        this.repository = repository;
        this.confirmationSender = confirmationSender;
    }

    public void process(Order order){
        if (order.isValid() && repository.save(order)) {
            confirmationSender.sendConfirmation(order);
        }
    }

}
