package com.example.as;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.as.adapter.OrderItemAdapter;
import com.example.as.model.Order;
import com.example.as.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderDetail extends AppCompatActivity {
    RecyclerView rvCheckoutItems;
    OrderItemAdapter adapter;
    List<OrderItem> orderItemList;
    int userId;
    String orderId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = getIntent().getIntExtra("userId",0);
        setContentView(R.layout.activity_order_detail);
        orderItemList = new ArrayList<>();
        orderId = getIntent().getStringExtra("orderId");
        System.out.println("orderID " + orderId);
        Utils.getDd(this);
        orderItemList = Utils.getOrderItem(userId, orderId);
        System.out.println("order item" + orderItemList);
        adapter = new OrderItemAdapter(this, orderItemList);
        rvCheckoutItems  = findViewById(R.id.rvCheckoutItem);
        rvCheckoutItems.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        rvCheckoutItems.setLayoutManager(new LinearLayoutManager(this));
    }
}