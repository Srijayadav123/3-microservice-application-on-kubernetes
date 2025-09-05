package com.example.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/orders")
public class OrderApplication {
    private static final List<Map<String, Object>> orders = new ArrayList<>();
    static {
        orders.add(Map.of("orderId", 101, "userId", 1, "item", "Laptop"));
        orders.add(Map.of("orderId", 102, "userId", 2, "item", "Phone"));
    }

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/{id}")
    public Map<String, Object> getOrder(@PathVariable int id) {
        return orders.stream()
            .filter(o -> (int)o.get("orderId") == id)
            .findFirst()
            .map(order -> {
                int userId = (int) order.get("userId");
                String userName = restTemplate.getForObject(
                        "http://user-service:8080/users/" + userId,
                        String.class
                );
                Map<String, Object> enriched = new HashMap<>(order);
                enriched.put("userName", userName);
                return enriched;
            })
            .orElse(Map.of("error", "Order not found"));
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}

