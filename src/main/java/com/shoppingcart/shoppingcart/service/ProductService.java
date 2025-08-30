package com.shoppingcart.shoppingcart.service;

import com.shoppingcart.shoppingcart.model.Product;
import com.shoppingcart.shoppingcart.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public  ProductService(ProductRepo productRepo){
        this.productRepo =productRepo;
    }

    public List<Product> allProduct(){
       return productRepo.findAll();
    }


    public  Product getProductById(Long productId){
        return productRepo.findById(productId)
                .orElseThrow( () -> new RuntimeException("Product Id not found"));
    }

    public Product addProduct(Product product){
       return productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
