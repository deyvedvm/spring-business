package dev.deyve.springbusiness.services;

import dev.deyve.springbusiness.models.Order;
import dev.deyve.springbusiness.models.Product;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public List<Order> getOrders() {

        return buildMockOrders();
    }

    private List<Order> buildMockOrders() {
        Product miBand = Product.builder().productId(UUID.randomUUID()).description("Mi Band 3").build();
        Product dellG3 = Product.builder().productId(UUID.randomUUID()).description("Notebook Dell G3").build();

        List<Product> products = Arrays.asList(miBand, dellG3);

        Order order = Order.builder().orderId(UUID.randomUUID()).products(products).build();

        logger.info("Order: {}", order);

        return Collections.singletonList(order);
    }
}
