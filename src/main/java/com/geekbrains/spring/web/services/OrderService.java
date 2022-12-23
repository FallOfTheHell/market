package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductsService productsService;

    public void createOrder(User user) {

    }
}
