package com.covidessentials.service;

import com.covidessentials.model.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    
    private Map<String, Order> orders = new HashMap<>();
    
    public OrderService() {
        initializeSampleOrders();
    }
    
    private void initializeSampleOrders() {
        // Sample Order 1
        Order order1 = new Order();
        order1.setId("ORD-001");
        order1.setCustomerId("1");
        order1.setCustomerName("John Doe");
        order1.setCustomerEmail("john.doe@email.com");
        order1.setCustomerPhone("+1-555-1001");
        order1.setShippingAddress("123 Main Street");
        order1.setCity("Springfield");
        order1.setZipCode("12345");
        order1.setSubtotal(67.97);
        order1.setShippingFee(9.99);
        order1.setTax(5.44);
        order1.setTotal(83.40);
        order1.setPaymentMethod("card");
        order1.setStatus("COMPLETED");
        order1.setOrderDate(LocalDateTime.now().minusDays(5));
        order1.setDeliveryDate(LocalDateTime.now().minusDays(3));
        
        List<Order.OrderItem> items1 = Arrays.asList(
            new Order.OrderItem("1", "N95 Face Masks (Pack of 10)", 29.99, 1),
            new Order.OrderItem("2", "Hand Sanitizer 500ml", 12.99, 2),
            new Order.OrderItem("3", "Vitamin C Tablets", 18.99, 1)
        );
        order1.setItems(items1);
        orders.put(order1.getId(), order1);

        // Sample Order 2
        Order order2 = new Order();
        order2.setId("ORD-002");
        order2.setCustomerId("2");
        order2.setCustomerName("Jane Smith");
        order2.setCustomerEmail("jane.smith@email.com");
        order2.setCustomerPhone("+1-555-1002");
        order2.setShippingAddress("456 Oak Avenue");
        order2.setCity("Riverside");
        order2.setZipCode("54321");
        order2.setSubtotal(89.96);
        order2.setShippingFee(0.00);
        order2.setTax(7.20);
        order2.setTotal(97.16);
        order2.setPaymentMethod("cod");
        order2.setStatus("CONFIRMED");
        order2.setOrderDate(LocalDateTime.now().minusDays(2));
        
        List<Order.OrderItem> items2 = Arrays.asList(
            new Order.OrderItem("4", "Surgical Face Masks (Pack of 50)", 19.99, 2),
            new Order.OrderItem("6", "Thermometer (Digital)", 35.99, 1),
            new Order.OrderItem("8", "Zinc Supplements", 16.99, 2)
        );
        order2.setItems(items2);
        orders.put(order2.getId(), order2);

        // Sample Order 3
        Order order3 = new Order();
        order3.setId("ORD-003");
        order3.setCustomerId("3");
        order3.setCustomerName("Mike Johnson");
        order3.setCustomerEmail("mike.johnson@email.com");
        order3.setCustomerPhone("+1-555-1003");
        order3.setShippingAddress("789 Pine Road");
        order3.setCity("Lakewood");
        order3.setZipCode("67890");
        order3.setSubtotal(44.98);
        order3.setShippingFee(9.99);
        order3.setTax(3.60);
        order3.setTotal(58.57);
        order3.setPaymentMethod("card");
        order3.setStatus("PENDING");
        order3.setOrderDate(LocalDateTime.now().minusHours(6));
        
        List<Order.OrderItem> items3 = Arrays.asList(
            new Order.OrderItem("5", "Antibacterial Wipes (Pack of 6)", 24.99, 1),
            new Order.OrderItem("1", "N95 Face Masks (Pack of 10)", 29.99, 1)
        );
        order3.setItems(items3);
        orders.put(order3.getId(), order3);

        // Sample Order 4
        Order order4 = new Order();
        order4.setId("ORD-004");
        order4.setCustomerId("4");
        order4.setCustomerName("Sarah Wilson");
        order4.setCustomerEmail("sarah.wilson@email.com");
        order4.setCustomerPhone("+1-555-1004");
        order4.setShippingAddress("321 Elm Street");
        order4.setCity("Greenville");
        order4.setZipCode("13579");
        order4.setSubtotal(125.95);
        order4.setShippingFee(0.00);
        order4.setTax(10.08);
        order4.setTotal(136.03);
        order4.setPaymentMethod("card");
        order4.setStatus("COMPLETED");
        order4.setOrderDate(LocalDateTime.now().minusDays(7));
        order4.setDeliveryDate(LocalDateTime.now().minusDays(5));
        
        List<Order.OrderItem> items4 = Arrays.asList(
            new Order.OrderItem("7", "KN95 Masks (Pack of 20)", 39.99, 2),
            new Order.OrderItem("6", "Thermometer (Digital)", 35.99, 1),
            new Order.OrderItem("8", "Zinc Supplements", 16.99, 3)
        );
        order4.setItems(items4);
        orders.put(order4.getId(), order4);

        // Sample Order 5
        Order order5 = new Order();
        order5.setId("ORD-005");
        order5.setCustomerId("5");
        order5.setCustomerName("David Brown");
        order5.setCustomerEmail("david.brown@email.com");
        order5.setCustomerPhone("+1-555-1005");
        order5.setShippingAddress("654 Maple Drive");
        order5.setCity("Hillside");
        order5.setZipCode("24680");
        order5.setSubtotal(31.98);
        order5.setShippingFee(9.99);
        order5.setTax(2.56);
        order5.setTotal(44.53);
        order5.setPaymentMethod("cod");
        order5.setStatus("CANCELLED");
        order5.setOrderDate(LocalDateTime.now().minusDays(1));
        
        List<Order.OrderItem> items5 = Arrays.asList(
            new Order.OrderItem("2", "Hand Sanitizer 500ml", 12.99, 1),
            new Order.OrderItem("3", "Vitamin C Tablets", 18.99, 1)
        );
        order5.setItems(items5);
        orders.put(order5.getId(), order5);
    }
    
    public Order createOrder(Order order) {
        String id = "ORD-" + System.currentTimeMillis();
        order.setId(id);
        orders.put(id, order);
        return order;
    }
    
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
    
    public Order getOrderById(String id) {
        return orders.get(id);
    }
    
    public Order updateOrderStatus(String id, String status) {
        Order order = orders.get(id);
        if (order != null) {
            order.setStatus(status);
            return order;
        }
        return null;
    }
    
    public List<Order> getOrdersByCustomerEmail(String email) {
        return orders.values().stream()
                .filter(order -> order.getCustomerEmail().equals(email))
                .collect(Collectors.toList());
    }
    
    public boolean deleteOrder(String id) {
        return orders.remove(id) != null;
    }
}