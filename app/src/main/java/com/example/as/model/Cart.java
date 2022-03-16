package com.example.as.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "carts")
public class Cart {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private long userId;
    private long shoeId;
    private long quantity;

    public Cart() {
    }

    public Cart(long userId, long shoeId, long quantity) {
        this.userId = userId;
        this.shoeId = shoeId;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getShoeId() {
        return shoeId;
    }

    public void setShoeId(long shoeId) {
        this.shoeId = shoeId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
