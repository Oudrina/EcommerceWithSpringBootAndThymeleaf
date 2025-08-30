package com.shoppingcart.shoppingcart.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cItemId;
    private int quantity;
    private double subTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getcItemId() {
        return cItemId;
    }


    public double getSubTotal() {
        return product.getPrice() * quantity;
    }


}
