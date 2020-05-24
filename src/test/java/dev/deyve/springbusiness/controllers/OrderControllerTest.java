package dev.deyve.springbusiness.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.deyve.springbusiness.models.Order;
import dev.deyve.springbusiness.models.Product;
import dev.deyve.springbusiness.services.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    private final String URL = "http://localhost:8080/api/orders";

    List<Order> ordersMock;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        Product miBandMock = Product.builder().productId(UUID.randomUUID()).description("Mi Band 3").build();
        Product dellG3Mock = Product.builder().productId(UUID.randomUUID()).description("Notebook Dell G3").build();

        List<Product> productsMock = Arrays.asList(miBandMock, dellG3Mock);

        Order orderMock = Order.builder().orderId(UUID.randomUUID()).products(productsMock).build();

        ordersMock = Collections.singletonList(orderMock);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Get Orders - should return all orders")
    void getOrdersTest() throws Exception {

        when(orderService.getOrders()).thenReturn(ordersMock);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        verify(orderService).getOrders();

        String contentAsString = result.getResponse().getContentAsString();
        List<Order> orders = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });

        System.out.println(orders);

        assertEquals(orders, ordersMock, "Incorrect Response content");
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus(), "Incorrect Response Status");
    }

}