package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.services.CartService;
import com.geekbrains.spring.web.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clear();
    }

    @GetMapping("/remove/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.remove(id);
    }

    @GetMapping("/change_quantity/{id}")
    public void changeScore(@RequestParam Long studentId, @RequestParam Integer delta) {
        cartService.changeQuantity(studentId, delta);
    }
}
