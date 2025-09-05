package com.covidessentials.model;

import java.time.LocalDateTime;

public class Product {
    private String id;
    private String name;
    private double price;
    private String image;
    private String category;
    private String description;
    private boolean inStock;
    private double rating;
    private int reviews;
    private int stockQuantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public Product() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Product(String id, String name, double price, String image, String category, 
                   String description, boolean inStock, double rating, int reviews, int stockQuantity) {
        this();
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
        this.description = description;
        this.inStock = inStock;
        this.rating = rating;
        this.reviews = reviews;
        this.stockQuantity = stockQuantity;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { 
        this.name = name; 
        this.updatedAt = LocalDateTime.now();
    }

    public double getPrice() { return price; }
    public void setPrice(double price) { 
        this.price = price; 
        this.updatedAt = LocalDateTime.now();
    }

    public String getImage() { return image; }
    public void setImage(String image) { 
        this.image = image; 
        this.updatedAt = LocalDateTime.now();
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { 
        this.category = category; 
        this.updatedAt = LocalDateTime.now();
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { 
        this.description = description; 
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isInStock() { return inStock; }
    public void setInStock(boolean inStock) { 
        this.inStock = inStock; 
        this.updatedAt = LocalDateTime.now();
    }

    public double getRating() { return rating; }
    public void setRating(double rating) { 
        this.rating = rating; 
        this.updatedAt = LocalDateTime.now();
    }

    public int getReviews() { return reviews; }
    public void setReviews(int reviews) { 
        this.reviews = reviews; 
        this.updatedAt = LocalDateTime.now();
    }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { 
        this.stockQuantity = stockQuantity; 
        this.inStock = stockQuantity > 0;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", inStock=" + inStock +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}