package com.shoppingcart.shoppingcart.service;

import com.shoppingcart.shoppingcart.model.CartItem;
import com.shoppingcart.shoppingcart.model.Product;
import com.shoppingcart.shoppingcart.model.ShopCart;
import com.shoppingcart.shoppingcart.repository.CartItemRepo;
import com.shoppingcart.shoppingcart.repository.ProductRepo;
import com.shoppingcart.shoppingcart.repository.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ShoppingCartService {
    private ShoppingCartRepo shoppingCartRepo;
    private ProductRepo productRepo;
    private CartItemRepo cartItemRepo;

    @Autowired
    public ShoppingCartService(ShoppingCartRepo shoppingCartRepo,ProductRepo productRepo,CartItemRepo cartItemRepo){
    this.shoppingCartRepo = shoppingCartRepo;
    this.productRepo = productRepo;
    this.cartItemRepo = cartItemRepo;
    }

    public List<ShopCart> shopCarts (){
      return   shoppingCartRepo.findAll();
    }

    public  ShopCart  addCart(Long productId , int quantity){
        ShopCart shopCart = new ShopCart();

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.getSubTotal();

        shopCart.getItemList().add(cartItem);
        System.out.println("Successfully added to cart");

        return shoppingCartRepo.save(shopCart);
    }
//Another and simpleier way of adding cartItem to cart , Just figures it out eyyyyyy

    public ShopCart cart( CartItem cartItem){
        ShopCart shopCart = new ShopCart();
        shopCart.getItemList().add(cartItem);
     return     shoppingCartRepo.save(shopCart);
    }

//    public void deleteCartItem(Long cartId){
//            ShopCart shopCart = (ShopCart) shoppingCartRepo.findAll();
//        shopCart.getItemList().remove(cartId);
//
//    }



    public double subTotal(Long cartItemId){
 CartItem cartItem = cartItemRepo.findById(cartItemId)
         .orElseThrow(() -> new RuntimeException("CartItem id not found"));

    double subTotal = cartItem.getSubTotal();
    cartItem.setSubTotal(subTotal);

    cartItemRepo.save(cartItem);
    return subTotal;

    }



    public double totalAmount(Long cartId){
        return shoppingCartRepo.findById(cartId).get().getTotalAmount();

    }

   public double  tAmount(Long cartId){

        ShopCart cart =  shoppingCartRepo.findById(cartId)
                .orElseThrow(()-> new RuntimeException("ShopCart ID not found"));

        double total = cart.getTotalAmount();
        cart.setTotalAmount(total);
        shoppingCartRepo.save(cart);
        return total;
   }




}
