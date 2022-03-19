package com.example.as;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.as.activity.Cart;
import com.example.as.activity.MainActivity;
import com.example.as.activity.Map;
import com.example.as.adapter.ShoeAdapter;
import com.example.as.model.CartItem;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ShoeAdapter adapter;
    private RecyclerView rvProducts;
    private Button btnCart, btnOrders;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.getDd(this);
        setContentView(R.layout.activity_home);
        btnCart = findViewById(R.id.btnViewCart);
        btnOrders = findViewById(R.id.btnOrders);
        userId =  getIntent().getIntExtra("userId",1);
        btnOrders.setOnClickListener(view -> {
            Intent intent = new Intent(this, OrderActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });
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
        Utils.getDd(this);
        List<CartItem> card = Utils.getCard(userId);
        btnCart = findViewById(R.id.btnViewCart);
        if(card == null){
            btnCart.setText("Cart(0)");
        }else {
            btnCart.setText("Cart("+card.size()+")");
        }
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.google_map, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.googleMapmenu: {
                Intent intent = new Intent(this, Map.class);
                startActivity(intent);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}