package com.ma.SpringBootDemo;

import org.springframework.stereotype.Component;

@Component
public class MySQLOrderRepository implements OrderRepository {

    @Override
    public boolean save(Order order) {
        System.out.println("Create MySQL Connection");
        System.out.println("Save order to Database: " + order.getId());
        return true;
    }

}
