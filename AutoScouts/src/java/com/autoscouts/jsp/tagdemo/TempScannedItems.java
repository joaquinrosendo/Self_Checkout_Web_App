
package com.autoscouts.jsp.tagdemo;

public class TempScannedItems {
    private String scanned_items;
    private float items_cost;
    private int items_quantity;
    private float total;

    public TempScannedItems(String scanned_items, float items_cost, int items_quantity) {
        this.scanned_items = scanned_items;
        this.items_cost = items_cost;
        this.items_quantity = items_quantity;
    }

    public TempScannedItems(String scanned_items, float items_cost, int items_quantity, float total) {
        this.scanned_items = scanned_items;
        this.items_cost = items_cost;
        this.items_quantity = items_quantity;
        this.total = total;
    }

    TempScannedItems(float total) {
        this.total = total;
    }

    public String getScanned_items() {
        return scanned_items;
    }

    public void setScanned_items(String scanned_items) {
        this.scanned_items = scanned_items;
    }

    public float getItems_cost() {
        return items_cost;
    }

    public void setItems_cost(float items_cost) {
        this.items_cost = items_cost;
    }

    public int getItems_quantity() {
        return items_quantity;
    }

    public void setItems_quantity(int items_quantity) {
        this.items_quantity = items_quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    
}
