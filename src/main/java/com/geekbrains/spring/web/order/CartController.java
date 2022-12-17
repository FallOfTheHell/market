package com.geekbrains.spring.web.order;


import com.geekbrains.spring.web.converters.ProductConverter;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.order.cart.Cart;
import com.geekbrains.spring.web.services.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@RestController
@Slf4j
public class CartController {
    private final Cart cart;
    private final ProductsService productService;

    @GetMapping
    public List<ProductDto> getCartProduct(){
        return cart.getPurchase();
    }

    @PostMapping
    public Cart addToCart(@RequestBody Long id){
        Optional<Product> product = Optional
                .ofNullable(productService.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found")));
        product.ifPresent(cart::addProductToCart);
        return cart;
    }

    @DeleteMapping
    public void clear(){
        cart.clear();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        cart.delete(id);
    }
}