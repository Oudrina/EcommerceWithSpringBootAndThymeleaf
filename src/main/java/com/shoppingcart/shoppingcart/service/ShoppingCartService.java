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


@Service
public class ShoppingCartService {
    private final ShoppingCartRepo shoppingCartRepo;
    private final ProductRepo productRepo;
    private final CartItemRepo cartItemRepo;

    @Autowired
    public ShoppingCartService(ShoppingCartRepo shoppingCartRepo, ProductRepo productRepo, CartItemRepo cartItemRepo) {
        this.shoppingCartRepo = shoppingCartRepo;
        this.productRepo = productRepo;
        this.cartItemRepo = cartItemRepo;
    }


    //        Fetch all the shopCart in the database
//        Loop or iterate through it shopCart to get the list of item in the shopCart itemList then return those list

    public List<ShopCart> allShopCart() {
        List<ShopCart> shopCarts = shoppingCartRepo.findAll();

        for (ShopCart cart : shopCarts) {
            cart.getItemList();
        }
        return shopCarts;
    }


    public ShopCart addCart(Long productId, int quantity) {
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


//Another and simpler way of adding cartItem to cart , Just figures it out eyyyyyy
    //        Create new ShopCart object;
    //        Add cartItem to the ShopCartList in the shopCart then save the ShopCart or
    //        get the shopItemList and add cartItem to it

    public ShopCart cart(CartItem cartItem) {
        ShopCart shopCart = new ShopCart();

        shopCart.getItemList().add(cartItem);
        return shoppingCartRepo.save(shopCart);
    }


    //        Find the shopCart itself by id
//        find the cartItem in the ShopCart itemList by id ,and it's id to the parameter id
//          get the itemList in the shopCart and remove it if the id os the shopCart equals the id passed
// Then delete that particular shopCart with its corresponding cartItem

    public void deleteCartItem(Long cartId) {
        ShopCart shopCart = shoppingCartRepo
                .findById(cartId).orElseThrow(() -> new RuntimeException("Cannot find  cartId" + cartId));

        CartItem cartItem = cartItemRepo.findById(cartId).orElseThrow(() -> new RuntimeException("Cannot find Id" + cartId));
        cartItem.setCItemId(cartId);

        shopCart.getItemList().removeIf(ShopCart -> shopCart.getCartId().equals(cartId));

        shoppingCartRepo.deleteById(cartId);
        cartItemRepo.deleteById(cartId);

    }

    public double tAmount(Long cartId) {

        ShopCart cart = shoppingCartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("ShopCart ID not found"));

        double total = cart.getTotalAmount();
        cart.setTotalAmount(total);
        shoppingCartRepo.save(cart);
        return total;
    }


}
