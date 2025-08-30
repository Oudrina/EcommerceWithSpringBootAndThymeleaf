package com.shoppingcart.shoppingcart.repository;

import com.shoppingcart.shoppingcart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Long> {

}
