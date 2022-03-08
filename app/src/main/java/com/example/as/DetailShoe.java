package com.example.as;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailShoe extends AppCompatActivity {
    TextView txtNameShoeDetail,txtPriceShoeDetail,txtDetailShoesDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shoe);

        txtNameShoeDetail=(TextView) findViewById(R.id.textViewNameDetail);
        txtPriceShoeDetail=(TextView) findViewById(R.id.textViewPriceDetail);
        txtDetailShoesDetail=(TextView) findViewById(R.id.textViewDetailDetail);



        txtNameShoeDetail.setText(AppUtil.ShoeName);
        txtPriceShoeDetail.setText(AppUtil.shoePrice);
        txtDetailShoesDetail.setText(AppUtil.shoeDetail);




    }

    @Override
    protected void onResume() {
        super.onResume();
        txtNameShoeDetail.setText(AppUtil.ShoeName);
        txtPriceShoeDetail.setText(AppUtil.shoePrice);
        txtDetailShoesDetail.setText("Detail : "+AppUtil.shoeDetail);

    }
}