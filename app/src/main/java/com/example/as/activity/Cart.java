package com.example.as.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.as.adapter.CartAdapter;
import com.example.as.R;
import com.example.as.model.AppUtil;
import com.example.as.model.Shoe;

import java.util.ArrayList;


public class Cart extends AppCompatActivity {
    ListView lvShoeCart;
    ArrayList<Shoe> arrayShoe;
    CartAdapter adapter;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        total=(TextView) findViewById(R.id.txtTotalCart);
        lvShoeCart=(ListView) findViewById(R.id.listviewShoeCart);

        arrayShoe=new ArrayList<Shoe>(AppUtil.cart.values());



        adapter=new CartAdapter(this,R.layout.dong_cart,arrayShoe);
        total.setText("Total price: "+String.valueOf(getTotalPriceCart())+ "$");
        lvShoeCart.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        adapter.notifyDataSetChanged();
        total.setText("Total price: "+String.valueOf(getTotalPriceCart())+ "$");
        super.onResume();
    }

    public int getTotalPriceCart(){
        int total=0;
        for (Shoe shoe:arrayShoe) {
            total=total+(Integer.parseInt(shoe.getPrice())*shoe.getQuantity());
        }
        return total;
    }
}