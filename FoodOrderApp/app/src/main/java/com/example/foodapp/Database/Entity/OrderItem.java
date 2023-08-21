package com.example.foodapp.Database.Entity;

public class OrderItem {
    private int id;
    private Food food_id;
    private Order order_id;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Food getFood_id() {
        return food_id;
    }

    public void setFood_id(Food food_id) {
        this.food_id = food_id;
    }

    public Order getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Order order_id) {
        this.order_id = order_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
