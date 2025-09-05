package com.covidessentials.model;

import java.time.LocalDateTime;

public class Service {
    private String id;
    private String name;
    private double price;
    private String duration;
    private String description;
    private String image;
    private boolean available;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public Service() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Service(String id, String name, double price, String duration, 
                   String description, String image, boolean available, String category) {
        this();
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.image = image;
        this.available = available;
        this.category = category;
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

    public String getDuration() { return duration; }
    public void setDuration(String duration) { 
        this.duration = duration; 
        this.updatedAt = LocalDateTime.now();
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { 
        this.description = description; 
        this.updatedAt = LocalDateTime.now();
    }

    public String getImage() { return image; }
    public void setImage(String image) { 
        this.image = image; 
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { 
        this.available = available; 
        this.updatedAt = LocalDateTime.now();
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { 
        this.category = category; 
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Service{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", duration='" + duration + '\'' +
                ", available=" + available +
                ", category='" + category + '\'' +
                '}';
    }
}