package com.shoppingcart.shoppingcart.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ShopCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    private double totalAmount;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> itemList = new ArrayList<>();


}
