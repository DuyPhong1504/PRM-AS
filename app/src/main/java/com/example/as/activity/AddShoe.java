package com.example.as;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AddShoe extends AppCompatActivity {
    Database database;
    TextInputEditText txtNameshoe, txtPriceshoe,txtDetail;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shoe);

        txtNameshoe = (TextInputEditText) findViewById(R.id.txtNameshoe);
        txtPriceshoe = (TextInputEditText) findViewById(R.id.txtPriceshoe);
        txtDetail=(TextInputEditText) findViewById(R.id.txtDetail);
        btnAdd = (Button) findViewById(R.id.btnAddshoe);

        database=new Database(this,"GhiChu.sqlite",null,1);

        database.QueryData("Create table if not exists Shoe(id Integer Primary Key Autoincrement," +
                "nameShoe nvarchar(200),price nvarchar(200),detail nvarchar(200))");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.QueryData("Insert into Shoe values(null,'" + txtNameshoe.getText().toString() + "'," +
                        "'" + txtPriceshoe.getText().toString() + "','"+txtDetail.getText().toString()+"')");
                txtNameshoe.setText("");
                txtPriceshoe.setText("");
                finish();

            }
        });
    }
}