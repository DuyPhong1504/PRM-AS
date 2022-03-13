package com.example.as.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.as.adapter.CartAdapter;
import com.example.as.R;

import java.util.ArrayList;


public class Cart extends AppCompatActivity {
    ListView lvShoeCart;
    ArrayList<Shoe> arrayShoe;
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        lvShoeCart=(ListView) findViewById(R.id.listviewShoeCart);

        arrayShoe=new ArrayList<Shoe>(AppUtil.cart.values());
        


        adapter=new CartAdapter(this,R.layout.dong_cart,arrayShoe);
        lvShoeCart.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        adapter.notifyDataSetChanged();
        super.onResume();
    }
}