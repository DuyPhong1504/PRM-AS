package com.example.as;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.as.adapter.OrderAdapter;
import com.example.as.model.Order;

import java.util.List;

public class OrderActivity extends AppCompatActivity {
    RecyclerView rvOrders;
    TextView tvTotalOrder;
    OrderAdapter orderAdapter;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = getIntent().getIntExtra("userId",0);
        Utils.getDd(this);
        List<Order> orders = Utils.getOrders(userId);
        setContentView(R.layout.activity_order);
        rvOrders = findViewById(R.id.rvOrders);
        if(orders == null){
            tvTotalOrder = findViewById(R.id.tvOrderTotal);
            //tvTotalOrder.setText("You have no order");
        }
        System.out.println(userId);
        System.out.println(orders);
        orderAdapter = new OrderAdapter(this,orders);
        rvOrders.setAdapter(orderAdapter);
        rvOrders.setLayoutManager(new LinearLayoutManager(this));
    }
}