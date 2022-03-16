package com.example.as.model;

import java.util.List;

public class Order {
    private String id;
    private double totals;
    private List<OrderItem> items;
    private Users users;

    public Order(String id, double totals, List<OrderItem> items) {
        this.id = id;
        this.totals = totals;
        this.items = items;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotals() {
        return totals;
    }

    public void setTotals(double totals) {
        this.totals = totals;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
