package com.example.as;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.as.adapter.CartAdapter;
import com.example.as.adapter.CheckoutItemAdapter;
import com.example.as.model.CartItem;
import com.example.as.model.Order;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class CheckoutActivity extends AppCompatActivity {
    TextView tvTotal;
    TextInputEditText etCity, etDistrict , etAddress, etWard;
    RecyclerView rvPreCheckoutCartItem;
    MaterialButton btnOrderNow;
    int userId;
    CheckoutItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.getDd(this);
        setContentView(R.layout.activity_checkout);
        Log.i("userId", getIntent().getIntExtra("userId",0)+"");
        userId = getIntent().getIntExtra("userId",0);
        binding();
        config();
    }


    private void binding(){
        etCity = findViewById(R.id.etCity);
        etDistrict = findViewById(R.id.etDistrict);
        etAddress = findViewById(R.id.etAddress);
        etWard = findViewById(R.id.etWard);
        btnOrderNow = findViewById(R.id.btnConfirm);
        rvPreCheckoutCartItem = findViewById(R.id.rvCheckoutItems);
        tvTotal = findViewById(R.id.tvTotalCost);
    }
    private void config(){
        Intent intent = this.getIntent();
        int uid = intent.getIntExtra("userId", 0);
        System.out.println("uid" + uid);
        Utils.getDd(this);
        List<CartItem> cardItem = Utils.getCard(uid);
        adapter = new CheckoutItemAdapter(this, cardItem);
        tvTotal.setText("Total: " + cardItem.stream().map(item -> item.getProduct().getPrice() * item.getQuantity()).reduce(0.0,(a, b) ->{return a+b;}).toString());
        rvPreCheckoutCartItem.setAdapter(adapter);
        rvPreCheckoutCartItem.setLayoutManager(new LinearLayoutManager(this));
        btnOrderNow.setOnClickListener(view -> {
            boolean validation = validation(etCity.getText().toString(), etDistrict.getText().toString(), etWard.getText().toString(), etAddress.getText().toString());
            if (validation) {
                boolean canCheckOut = Utils.checkoutCart(uid);
                if(canCheckOut){
                    Order checkout = Utils.checkout(uid);
                    Intent detailIntent = new Intent(this, OrderDetail.class);
                    detailIntent.putExtra("orderId", checkout.getId());
                    startActivity(detailIntent);
                }else{
                    Toast.makeText(this, "Cart item is not available please remove them", Toast.LENGTH_LONG);
                }
            }
        });
    }

    private boolean validation(String city, String district, String ward, String address){
        boolean isValid = true;
        if(city == null ||city.trim().isEmpty()){
            etCity.setError("Require");
            isValid  = false;
        }
        if(district == null || district.trim().isEmpty()){
            etDistrict.setError("Require");
            isValid = false;
        }if(ward == null || ward.trim().isEmpty()){
            etWard.setError("Require");
            isValid = false;
        }if(address == null || address.trim().isEmpty()){
            etAddress.setError("Require");
            isValid =false;
        }
        return isValid;
    }
    
}