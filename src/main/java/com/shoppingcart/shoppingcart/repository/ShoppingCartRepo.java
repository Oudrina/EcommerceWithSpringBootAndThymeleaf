package com.shoppingcart.shoppingcart.repository;

import com.shoppingcart.shoppingcart.model.ShopCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShopCart,Long> {
}
