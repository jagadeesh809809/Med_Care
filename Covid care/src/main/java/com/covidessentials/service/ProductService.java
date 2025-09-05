package com.covidessentials.service;

import com.covidessentials.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    
    private Map<String, Product> products = new HashMap<>();
    
    public ProductService() {
        initializeProducts();
    }
    
    private void initializeProducts() {
        // Initialize with sample data
        products.put("1", new Product("1", "N95 Face Masks (Pack of 10)", 29.99, 
            "https://images.pexels.com/photos/3952241/pexels-photo-3952241.jpeg?auto=compress&cs=tinysrgb&w=300",
            "masks", "Medical-grade N95 masks with 95% filtration efficiency", true, 4.8, 324, 50));
            
        products.put("2", new Product("2", "Hand Sanitizer 500ml", 12.99,
            "https://images.pexels.com/photos/4039921/pexels-photo-4039921.jpeg?auto=compress&cs=tinysrgb&w=300",
            "sanitizers", "70% alcohol-based hand sanitizer, WHO recommended formula", true, 4.6, 156, 100));
            
        products.put("3", new Product("3", "Vitamin C Tablets", 18.99,
            "https://images.pexels.com/photos/3683107/pexels-photo-3683107.jpeg?auto=compress&cs=tinysrgb&w=300",
            "medicines", "Immune system support with 1000mg Vitamin C", true, 4.7, 89, 75));
            
        products.put("4", new Product("4", "Surgical Face Masks (Pack of 50)", 19.99,
            "https://images.pexels.com/photos/4386370/pexels-photo-4386370.jpeg?auto=compress&cs=tinysrgb&w=300",
            "masks", "Disposable 3-layer surgical masks with ear loops", true, 4.5, 267, 200));
            
        products.put("5", new Product("5", "Antibacterial Wipes (Pack of 6)", 24.99,
            "https://images.pexels.com/photos/4033148/pexels-photo-4033148.jpeg?auto=compress&cs=tinysrgb&w=300",
            "sanitizers", "Disinfecting wipes that kill 99.9% of germs", true, 4.4, 198, 80));
            
        products.put("6", new Product("6", "Thermometer (Digital)", 35.99,
            "https://images.pexels.com/photos/4386467/pexels-photo-4386467.jpeg?auto=compress&cs=tinysrgb&w=300",
            "medicines", "Non-contact infrared thermometer with LCD display", true, 4.9, 445, 30));
            
        products.put("7", new Product("7", "KN95 Masks (Pack of 20)", 39.99,
            "https://images.pexels.com/photos/3952242/pexels-photo-3952242.jpeg?auto=compress&cs=tinysrgb&w=300",
            "masks", "Premium KN95 masks with 5-layer filtration", true, 4.7, 312, 60));
            
        products.put("8", new Product("8", "Zinc Supplements", 16.99,
            "https://images.pexels.com/photos/3683111/pexels-photo-3683111.jpeg?auto=compress&cs=tinysrgb&w=300",
            "medicines", "Immune support with 15mg zinc per tablet", true, 4.3, 67, 90));
    }
    
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    
    public Product getProductById(String id) {
        return products.get(id);
    }
    
    public List<Product> getProductsByCategory(String category) {
        return products.values().stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }
    
    public Product createProduct(Product product) {
        String id = UUID.randomUUID().toString();
        product.setId(id);
        products.put(id, product);
        return product;
    }
    
    public Product updateProduct(String id, Product updatedProduct) {
        if (products.containsKey(id)) {
            updatedProduct.setId(id);
            products.put(id, updatedProduct);
            return updatedProduct;
        }
        return null;
    }
    
    public boolean deleteProduct(String id) {
        return products.remove(id) != null;
    }
    
    public Product updateStock(String id, int quantity) {
        Product product = products.get(id);
        if (product != null) {
            product.setStockQuantity(quantity);
            return product;
        }
        return null;
    }
}