package com.ma.SpringBootDemo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "notification", name = "service", havingValue = "sms")
public class ConfirmationSmsSender implements ConfirmationSender {

    @Override
    public void sendConfirmation(Order order) {
        String name = order.getCustomerName();
        String phone = order.getPhoneNumber();
        System.out.println("Send confirmation to " + name + " on " + phone);
    }
}
