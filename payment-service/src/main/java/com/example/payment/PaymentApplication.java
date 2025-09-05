package com.example.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/payments")
public class PaymentApplication {
    private static final List<Map<String, Object>> payments = new ArrayList<>();
    static {
        payments.add(Map.of("paymentId", 1001, "orderId", 101, "amount", 1200));
        payments.add(Map.of("paymentId", 1002, "orderId", 102, "amount", 800));
    }

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public List<Map<String, Object>> getPayments() {
        List<Map<String, Object>> enriched = new ArrayList<>();
        for (Map<String, Object> payment : payments) {
            int orderId = (int) payment.get("orderId");
            Map order = restTemplate.getForObject(
                    "http://order-service:8080/orders/" + orderId,
                    Map.class
            );
            Map<String, Object> copy = new HashMap<>(payment);
            copy.put("orderDetails", order);
            enriched.add(copy);
        }
        return enriched;
    }

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }
}

