package com.example.as;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database database;
    ListView lvShoe;
    ArrayList<Shoe> arrayShoe;
    ShoeAdapter adapter;
    Intent i;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_shoe,menu);
        getMenuInflater().inflate(R.menu.cart,menu);
        getMenuInflater().inflate(R.menu.google_map,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuAdd){
            i = new Intent(this, AddShoe.class);
            startActivity(i);
        }

        if(item.getItemId()==R.id.menuCart){
            i = new Intent(this, Cart.class);
            startActivity(i);
        }
        if(item.getItemId()==R.id.googleMapmenu){
            i = new Intent(this, Map.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

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
                "nameShoe nvarchar(200),price nvarchar(200),detail nvarchar(300))");
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

    @Override
    protected void onResume() {
        arrayShoe.clear();
        database.QueryData("Create table if not exists Shoe(id Integer Primary Key Autoincrement," +
                "nameShoe nvarchar(200),price nvarchar(200),detail nvarchar(300))");
        Cursor dataShoe=database.GetData("Select * from Shoe");
        while (dataShoe.moveToNext()){
            String name=dataShoe.getString(1);
            int id=dataShoe.getInt(0);
            String price=dataShoe.getString(2);
            arrayShoe.add(new Shoe(id,name,price));

        }
        adapter.notifyDataSetChanged();

        super.onResume();
    }
}