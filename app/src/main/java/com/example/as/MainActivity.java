package com.example.as;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database database;
    ListView lvShoe;
    ArrayList<Shoe> arrayShoe;
    ShoeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvShoe=(ListView) findViewById(R.id.listviewShoe);


        arrayShoe=new ArrayList<>();
        adapter=new ShoeAdapter(this,R.layout.dong_shoe,arrayShoe);
        lvShoe.setAdapter(adapter);

        database=new Database(this,"GhiChu.sqlite",null,1);

        database.QueryData("Create table if not exists Shoe(id Integer Primary Key Autoincrement," +
                "nameShoe nvarchar(200),price nvarchar(200))");
//
//        database.QueryData("Insert into Shoe values(null,'Adidas','200$')");
//        database.QueryData("Insert into Shoe values(null,'Adidas','299$')");

        Cursor dataShoe=database.GetData("Select * from Shoe");
        while (dataShoe.moveToNext()){
            String name=dataShoe.getString(1);
            int id=dataShoe.getInt(0);
            String price=dataShoe.getString(2);
            arrayShoe.add(new Shoe(id,name,price));

        }
        adapter.notifyDataSetChanged();

    }
}