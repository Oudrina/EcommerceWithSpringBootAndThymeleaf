package com.shoppingcart.shoppingcart.service;

import com.shoppingcart.shoppingcart.model.CartItem;
import com.shoppingcart.shoppingcart.repository.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    @Autowired
    public CartItemRepo cartItemRepo;


    public double subTotal(Long cartItemId) {
        CartItem cartItem = cartItemRepo.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem id not found"));

        double subTotal = cartItem.getSubTotal();
        cartItem.setSubTotal(subTotal);


        cartItemRepo.save(cartItem);
        return subTotal;

    }
}
