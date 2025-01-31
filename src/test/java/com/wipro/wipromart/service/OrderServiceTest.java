package com.wipro.wipromart.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wipro.wipromart.entity.Customer;
import com.wipro.wipromart.entity.Order;
import com.wipro.wipromart.entity.OrderItem;
import com.wipro.wipromart.entity.Product;
import com.wipro.wipromart.exception.ResourceNotFoundException;
import com.wipro.wipromart.repository.OrderRepository;

class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductService productService;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveOrder() {
        // Arrange
        Customer customer = new Customer();
        customer.setCustomerId(1);

        Product product = new Product();
        product.setProductId(1);
        product.setProductPrice(100.0);

        OrderItem orderItem = new OrderItem();
        orderItem.setQty(2);
        orderItem.setProductId(1);

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderItems(Arrays.asList(orderItem));

        when(customerService.fetchCustomerById(1)).thenReturn(customer);
        when(productService.getProductById(1)).thenReturn(product);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // Act
        Order savedOrder = orderService.saveOrder(order);

        // Assert
        assertNotNull(savedOrder);
        assertEquals(200.0, savedOrder.getOrderAmount());
        assertEquals("Success", savedOrder.getOrderStatus());
        assertNotNull(savedOrder.getOrderDate());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testGetOrderById_Success() {
        // Arrange
        Order order = new Order();
        order.setOrderId(1);
        order.setOrderStatus("Success");

        when(orderRepository.findById(1)).thenReturn(Optional.of(order));

        // Act
        Order fetchedOrder = orderService.getOrderById(1);

        // Assert
        assertNotNull(fetchedOrder);
        assertEquals(1, fetchedOrder.getOrderId());
        assertEquals("Success", fetchedOrder.getOrderStatus());
        verify(orderRepository, times(1)).findById(1);
    }

    @Test
    void testGetOrderById_NotFound() {
        // Arrange
        when(orderRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> orderService.getOrderById(1));
        verify(orderRepository, times(1)).findById(1);
    }
}
