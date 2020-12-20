package com.ma.SpringBootDemo;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderProcessorController {

    private final OrderProcessor orderProcessor;

    @GetMapping
    public String processOrder(Model model, HttpServletRequest request) {
        Order order = new Order("1", "Customer", "Email", "PhoneNumber");

        orderProcessor.process(order);

        model.addAttribute("name", order.getCustomerName());
        return "greeting";
    }
}
