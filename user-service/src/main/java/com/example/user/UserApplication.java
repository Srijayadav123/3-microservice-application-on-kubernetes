package com.example.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/users")
public class UserApplication {
    private static final Map<Integer, String> users = new HashMap<>();
    static {
        users.put(1, "Alice");
        users.put(2, "Bob");
        users.put(3, "Charlie");
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id) {
        return users.getOrDefault(id, "Unknown User");
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}

