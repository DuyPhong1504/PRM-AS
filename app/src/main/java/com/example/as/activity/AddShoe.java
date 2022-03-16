package com.example.as.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.as.database.Database;
import com.example.as.R;
import com.example.as.database.EntityDatabase;
import com.example.as.model.ShoeV2;
import com.google.android.material.textfield.TextInputEditText;

public class AddShoe extends AppCompatActivity {
    Database database;
    TextInputEditText txtNameshoe, txtPriceshoe,txtDetail, txtQuantity;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shoe);

        txtNameshoe = (TextInputEditText) findViewById(R.id.txtNameshoe);
        txtPriceshoe = (TextInputEditText) findViewById(R.id.txtPriceshoe);
        txtDetail=(TextInputEditText) findViewById(R.id.txtDetail);
        txtQuantity=(TextInputEditText) findViewById(R.id.txtQuantity);
        btnAdd = (Button) findViewById(R.id.btnAddshoe);

        database=new Database(this,"GhiChu.sqlite",null,1);

        database.QueryData("Create table if not exists Shoe(id Integer Primary Key Autoincrement," +
                "nameShoe nvarchar(200),price nvarchar(200),detail nvarchar(200))");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtNameshoe.getText().toString();
                int price = Integer.parseInt(txtPriceshoe.getText().toString());
                String detail = txtDetail.getText().toString();
                int quantity = Integer.parseInt(txtQuantity.getText().toString());

                database.QueryData("Insert into Shoe values(null,'" + txtNameshoe.getText().toString() + "'," +
                        "'" + txtPriceshoe.getText().toString() + "','"+txtDetail.getText().toString()+"')");


                ShoeV2 shoe = new ShoeV2(name, price, quantity, detail);
                long id = EntityDatabase.getInstance(getApplicationContext()).getShoeV2Dao().insert(shoe);
                System.out.println(id);

                txtNameshoe.setText("");
                txtPriceshoe.setText("");
                finish();

            }
        });
    }
}