package com.example.as.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.as.Utils;
import com.example.as.adapter.CartAdapter;
import com.example.as.R;
import com.example.as.model.AppUtil;
import com.example.as.model.CartItem;
import com.example.as.model.Product;
import com.example.as.model.Shoe;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;


public class Cart extends AppCompatActivity {
    RecyclerView lvShoeCart;
    List<CartItem> arrayShoe;
    CartAdapter adapter;
    TextView total;
    MaterialButton btnCheckout;
    int userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        total=(TextView) findViewById(R.id.tvTotal);
        btnCheckout = findViewById(R.id.btnCheckout);
        lvShoeCart= findViewById(R.id.rvcartItems);
        userId = getIntent().getIntExtra("userId", 0);
        arrayShoe = Utils.getCard(userId);
        adapter=new CartAdapter(this, arrayShoe);
        adapter.setUserId(userId);
        adapter.setClick(() ->{
            arrayShoe = Utils.getCard(userId);
            total.setText("Total price: "+String.valueOf(getTotalPriceCart())+ "$");
        });
        total.setText("Total price: "+String.valueOf(getTotalPriceCart())+ "$");
        lvShoeCart.setAdapter(adapter);
        lvShoeCart.setLayoutManager(new LinearLayoutManager(this));

    }
    public double getTotalPriceCart(){
        double total=0.0;
        if(arrayShoe == null){
            btnCheckout.setEnabled(false);
            return total;
        }
        btnCheckout.setEnabled(true);
        for (CartItem item: arrayShoe
             ) {
             total = total + item.getQuantity() * item.getProduct().getPrice();
        }
        return total;
    }
}