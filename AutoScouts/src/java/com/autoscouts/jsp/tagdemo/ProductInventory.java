
package com.autoscouts.jsp.tagdemo;

public class ProductInventory {
    private int id;
    private String item;
    private String description;
    private float price;
    private float discount;
    private int quantity;
    
    public ProductInventory(String item) {
        this.item = item;
    }
    
    // for restock quantity
    public ProductInventory(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
    
    public ProductInventory(String item, String description, float price, float discount) {
        this.item = item;
        this.description = description;
        this.price = price;
        this.discount = discount;
    }
    
    public ProductInventory(int id, String item, String description, float price, float discount) {
        this.id = id;
        this.item = item;
        this.description = description;
        this.price = price;
        this.discount = discount;
    }
    
    public ProductInventory(int id, String item, String description, float price, float discount, int quantity) {
        this.id = id;
        this.item = item;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
    }

    // for customer list on customer scanner page
    public ProductInventory(int id, String item, float price) {
        this.id = id;
        this.item = item;
        this.price = price;
    }

    public ProductInventory(int quantity) {
        this.quantity = quantity;
    }

    ProductInventory(String item, int quantity, float price) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ProductInventory{" + "id= " + id + "item=" + item + ", description=" + description + ", price=" + price + ", discount=" + discount + '}';
    }
 
}
