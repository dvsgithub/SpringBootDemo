package com.ma.SpringBootDemo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "notification", name = "service", havingValue = "email")
public class ConfirmationEmailSender implements ConfirmationSender {

    @Override
    public void sendConfirmation(Order order) {
        String name = order.getCustomerName();
        String email = order.getCustomerEmail();
        System.out.println("Send confirmation to " + name + " on " + email);
    }

}
