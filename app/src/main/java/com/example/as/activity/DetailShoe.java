package com.example.as.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.as.R;
import com.example.as.Utils;
import com.example.as.model.AppUtil;
import com.example.as.model.CartItem;
import com.example.as.model.Product;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class DetailShoe extends AppCompatActivity {
    TextView txtNameShoeDetail,txtPriceShoeDetail,txtDetailShoesDetail;
    Product product;
    MaterialButton btnIncrease, btnDecrease, btnAddCart;
    EditText etItemQuantity;
    int count;
    CartItem item;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shoe);
        Intent intent = this.getIntent();
        intent.getIntExtra("productId", 1);
        txtNameShoeDetail=(TextView) findViewById(R.id.tvProductName);
        txtPriceShoeDetail=(TextView) findViewById(R.id.tvProductPrice);
        txtDetailShoesDetail=(TextView) findViewById(R.id.tvProductDescription);
        btnAddCart = findViewById(R.id.btnAddCart);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnDecrease = findViewById(R.id.btnDecrease);
        etItemQuantity = findViewById(R.id.etItemQuantity);
        config();
    }

    private void config(){
        Intent intent = this.getIntent();
        userId = intent.getIntExtra("userId", 0);
        product = Utils.findProductById(intent.getIntExtra("productId",0));
        if(product == null){
            Log.w("product id",intent.getIntExtra("productId",0)+"");

        }else{
            Log.w("product id",intent.getIntExtra("productId",0)+"");
            Log.w("product", product.getName());
            txtNameShoeDetail.setText(product.getName());
            txtDetailShoesDetail.setText(product.getDescription());
            txtPriceShoeDetail.setText(product.getPrice()+"");
        }
        btnIncrease.setOnClickListener(view -> {
            count = count + 1;
            etItemQuantity.setText(count+"");
        });
        btnDecrease.setOnClickListener(view -> {
            count = count - 1  > 0 ? count - 1 : 1;
            etItemQuantity.setText(count+"");
        });
        btnAddCart.setOnClickListener(view -> {
            item = new CartItem(1, product, Integer.parseInt(etItemQuantity.getText().toString()));
            Utils.addCard(item,userId,count);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        config();
    }
}