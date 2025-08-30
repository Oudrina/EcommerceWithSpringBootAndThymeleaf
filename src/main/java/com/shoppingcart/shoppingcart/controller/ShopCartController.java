package com.shoppingcart.shoppingcart.controller;

import com.shoppingcart.shoppingcart.model.CartItem;
import com.shoppingcart.shoppingcart.model.Product;
import com.shoppingcart.shoppingcart.model.ShopCart;
import com.shoppingcart.shoppingcart.service.ProductService;
import com.shoppingcart.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
public class ShopCartController {

    private ShoppingCartService shoppingCartService;
    private ProductService productService;
    @Autowired
    public  ShopCartController(ShoppingCartService shoppingCartService , ProductService productService){
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }
    @GetMapping("allCarts")
      public  List<ShopCart> getAllShopCart(){
        List<ShopCart> shopCarts = shoppingCartService.shopCarts();
        return  shopCarts;
      }

    @PostMapping("/add")
    public void addToCart( @RequestParam Long productId, @RequestParam int quantity){
         shoppingCartService.addCart( productId, quantity);
//        Product product = productService.getProductById(productId);
//        model.addAttribute("shopCart",shopCart);
//        model.addAttribute("product",product);
//         return "redirect:/getProduct/{productId}";

    }

    @PostMapping("addCart")
    public ShopCart ShopCart(@RequestBody CartItem cartItem){
        return shoppingCartService.cart(cartItem);
    }

    @PostMapping("subTotal/{cartItemId}")
    public double getSubTotal( @PathVariable  Long cartItemId){
       return    shoppingCartService.subTotal(cartItemId);
    }

    @GetMapping("/{cartId}/total")
    public Double allAmount(@PathVariable Long cartId){
       return shoppingCartService.tAmount(cartId);
    }

}
