package com.shoppingcart.shoppingcart.controller;

import com.shoppingcart.shoppingcart.model.CartItem;
import com.shoppingcart.shoppingcart.model.Product;
import com.shoppingcart.shoppingcart.model.ShopCart;
import com.shoppingcart.shoppingcart.service.CartItemService;
import com.shoppingcart.shoppingcart.service.ProductService;
import com.shoppingcart.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//@RestController
@Controller
@RequestMapping("cart")
public class ShopCartController {

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final CartItemService cartItemService;
    @Autowired
    public  ShopCartController(ShoppingCartService shoppingCartService , ProductService productService ,CartItemService cartItemService){
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.cartItemService = cartItemService;
    }
    @GetMapping("allCarts")
      public String  getAllShopCart( Model model){
        List<ShopCart> cartList = shoppingCartService.allShopCart();
       model.addAttribute("shopCart" ,cartList);

     return  "redirect:/product/all";
      }


    @PostMapping("addCart")
    public String  ShopCart(@ModelAttribute CartItem cartItem,Model model){
          ShopCart shopCart = shoppingCartService.cart(cartItem);
        model.addAttribute("shopCart",shopCart);
        return "products";
    }

    @GetMapping("/{cartId}/total")
    public Double allAmount(@PathVariable Long cartId){
        return shoppingCartService.tAmount(cartId);
    }

    @PostMapping("subTotal/{cartItemId}")
    public double getSubTotal( @PathVariable  Long cartItemId){
       return    cartItemService.subTotal(cartItemId);
    }

     @DeleteMapping("removeItem/{cartId}")
    public  void   removeCartItem(@PathVariable Long cartId ){
       shoppingCartService.deleteCartItem(cartId);
    }


}
