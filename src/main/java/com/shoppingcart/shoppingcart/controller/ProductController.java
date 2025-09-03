package com.shoppingcart.shoppingcart.controller;

import com.shoppingcart.shoppingcart.model.Product;
import com.shoppingcart.shoppingcart.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;

    public ProductController ( ProductService productService) {
      this.productService = productService;
    }



    @GetMapping("/all" )
    public String allProduct(Model model){
        List<Product> products = productService.allProduct();
        model.addAttribute("products",products);
        return  "products";
    }

    @GetMapping("getProduct/{productId}")
    public String getProductById(Model model , @PathVariable Long productId){
        Product product = productService.getProductById(productId);
        model.addAttribute("product",product);
        return "productDetail";

    }

    @GetMapping("/create")
      public String createProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "AddProduct";
    }
    @PostMapping("/add")
    public String addProduct( @ModelAttribute Product product ,Model model){
        Product product1 = productService.addProduct(product);
        model.addAttribute("product" , product1);
        return "redirect:/product/all";
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id){
      productService.deleteProduct(id);
    }

}
