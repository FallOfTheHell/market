package com.geekbrains.spring.web.model;

import com.geekbrains.spring.web.entities.Product;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(Product product) { // TODO: Доработать в ДЗ
        for (CartItem item : items) {
            if (item.getProductId().equals(product.getId())) {
                item.incrementQuantity();
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        items.forEach(i -> totalPrice = totalPrice.add(i.getPrice()));
    }

    public CartItem findById(Long id) {
        return items.stream().filter(item -> item.getProductId().equals(id)).findFirst().get();
    }

    public void remove(Long productId) {
        if (items.removeIf(item -> item.getProductId().equals(productId))) {
            recalculate();
        }
    }

    public void clear() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    @Transactional
    public void changeQuantity(Long productId, Integer delta) {
        CartItem cartItem = findById(productId);
        cartItem.setProductId(cartItem.getProductId() + delta);
        // studentRepository.save(student);
    }
}
