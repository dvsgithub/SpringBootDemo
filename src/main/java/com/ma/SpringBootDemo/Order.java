package com.ma.SpringBootDemo;

public class Order {

    private String id;
    private String customerName;
    private String customerEmail;
    private String phoneNumber;

    public Order(String id, String customerName, String customerEmail, String phoneNumber) {
        this.id = id;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isValid() {
        return true;
    }

}
