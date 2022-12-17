package com.geekbrains.spring.web.order.cart;

import com.geekbrains.spring.web.converters.ProductConverter;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Cart {
    private ProductConverter productConverter;
    private List<ProductDto> purchase;
    private String title;
    private int quantity;
    private int cost;

    @PostConstruct
    public void createCart(){
        purchase = new ArrayList<>();
    }

    public void addProductToCart(Product product){
        ProductDto productDto = productConverter.entityToDto(product);
        getPurchase().add(productDto);
        setQuantity(getPurchase().size());
        log.debug(String.valueOf(getPurchase()));
    }

    public void delete(Long id){
        List<ProductDto> cartProduct = getPurchase();
        for (int i = 0; i < cartProduct.size(); i++) {
            if (cartProduct.get(i).getId().equals(id)){
                cartProduct.remove(i);
            }
        }
    }

    public void clear(){
        getPurchase().clear();
    }
}
