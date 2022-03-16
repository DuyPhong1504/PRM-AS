package com.example.as;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.as.activity.Cart;
import com.example.as.activity.MainActivity;
import com.example.as.adapter.ShoeAdapter;
import com.example.as.model.CartItem;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ShoeAdapter adapter;
    private RecyclerView rvProducts;
    private Button btnCart;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.getDd(this);
        setContentView(R.layout.activity_home);
        btnCart = findViewById(R.id.btnViewCart);
        userId =  getIntent().getIntExtra("userId",1);
        adapter = new ShoeAdapter(this, Utils.loadProduct(), userId);
        adapter.setCardClick(() ->{
            List<CartItem> card = Utils.getCard(userId);
            if(card == null){
                btnCart.setText("Cart(0)");
            }else {
                btnCart.setText("Cart("+card.size()+")");
            }
        });
        rvProducts = findViewById(R.id.rvProducts);
        rvProducts.setLayoutManager(new GridLayoutManager(this, 2));
        rvProducts.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        List<CartItem> card = Utils.getCard(userId);
        btnCart.setOnClickListener(view -> {
            Intent intent = new Intent(this, Cart.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });
        if(card == null){
            btnCart.setText("Cart(0)");
        }else {
            btnCart.setText("Cart("+card.size()+")");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<CartItem> card = Utils.getCard(userId);
        btnCart = findViewById(R.id.btnViewCart);
        if(card == null){
            btnCart.setText("Cart(0)");
        }else {
            btnCart.setText("Cart("+card.size()+")");
        }
    }
}