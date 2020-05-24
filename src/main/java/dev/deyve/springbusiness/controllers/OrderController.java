package dev.deyve.springbusiness.controllers;

import dev.deyve.springbusiness.models.Order;
import dev.deyve.springbusiness.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    @GetMapping
    public List<Order> getOrders() {

        List<Order> orders = orderService.getOrders();

        logger.info("Orders: {}", orders);

        return orders;
    }
}
