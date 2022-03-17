package com.example.as.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.as.R;
import com.example.as.Utils;
import com.example.as.adapter.ProductAdapter;
import com.example.as.adapter.ProductRecycleAdapter;
import com.example.as.model.Product;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    Button btnAdd, btnSave;
    ListView lvProducts;
    RecyclerView rvAdminProducts;
    List<Product> productList = new ArrayList<>();
    ProductAdapter productAdapter;
    ProductRecycleAdapter productRecycleAdapter;
    EditText etProductName, etProductPrice, etProductQuantity, etProductDescription;
    Integer productId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        btnAdd = findViewById(R.id.btnAdd);
        btnSave = findViewById(R.id.btnSave);
//
        etProductName = findViewById(R.id.etProductName);
        etProductPrice = findViewById(R.id.etProductPrice);
        etProductQuantity = findViewById(R.id.etProductQuantity);
        etProductDescription = findViewById(R.id.etProductDescription);

//        lvProducts = findViewById(R.id.lvProducts);
        rvAdminProducts = findViewById(R.id.rvAdminProducts);
//        rvAdminProducts.setLayoutManager(mLayoutManager);
        productList = Utils.loadProduct();
        System.out.println(productList.size());
//        productAdapter = new ProductAdapter(productList);
        productRecycleAdapter = new ProductRecycleAdapter(productList);
        productRecycleAdapter.itemListener = (id) -> {
            productId = id;
            Product product = Utils.findProductById(id);
            etProductName.setText(product.getName());
            etProductPrice.setText(product.getPrice() + "");
            etProductQuantity.setText(product.getQuantity() + "");
            etProductDescription.setText(product.getDescription());
        };

//        lvProducts.setAdapter(productAdapter);
        rvAdminProducts.setAdapter(productRecycleAdapter);
        rvAdminProducts.setLayoutManager(new LinearLayoutManager(this));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearInput();
            }
        });
        

        
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etProductName.getText().toString();
                String strPrice = etProductPrice.getText().toString();
                String strQuantity = etProductQuantity.getText().toString();
                String description = etProductDescription.getText().toString();
                
                if (name.isEmpty() || strPrice.isEmpty() || strQuantity.isEmpty() || description.isEmpty()) {
                    Toast.makeText(AdminActivity.this, "Not blank", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                double price = Double.parseDouble(strPrice);
                int quantity = Integer.parseInt(strQuantity);

                Product product = new Product(0, name, price, quantity, description);
                if (productId == null) {
                    Utils.insertNewProduct(product);
                    Toast.makeText(AdminActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Utils.updateProduct(productId, product);
                    Toast.makeText(AdminActivity.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                }
                clearInput();
                loadData();
                
            }
        });
        loadData();
    }

    public void addProductDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_product);

        EditText etProductName = dialog.findViewById(R.id.etProductName);
        EditText etProductPrice = dialog.findViewById(R.id.etProductPrice);
        EditText etProductQuantity = dialog.findViewById(R.id.etProductQuantity);
        EditText etProductDescription = dialog.findViewById(R.id.etProductDescription);

        Button btnSave = (Button) dialog.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etProductName.getText().toString();
                double price = Double.parseDouble(etProductPrice.getText().toString());
                int quantity = Integer.parseInt(etProductQuantity.getText().toString());
                String description = etProductDescription.getText().toString();

                Utils.insertNewProduct(new Product(0, name, price, quantity, description));
                Toast.makeText(AdminActivity.this, "Saved succesfully", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    public void updateProductDialog(int id, Product product) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_product);

        EditText etProductName = dialog.findViewById(R.id.etProductName);
        EditText etProductPrice = dialog.findViewById(R.id.etProductPrice);
        EditText etProductQuantity = dialog.findViewById(R.id.etProductQuantity);
        EditText etProductDescription = dialog.findViewById(R.id.etProductDescription);

        Button btnSave = (Button) dialog.findViewById(R.id.btnSave);

        etProductName.setText(product.getName());
        etProductPrice.setText(product.getPrice() + "");
        etProductQuantity.setText(product.getQuantity() + "");
        etProductDescription.setText(product.getDescription());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etProductName.getText().toString();
                double price = Double.parseDouble(etProductPrice.getText().toString());
                int quantity = Integer.parseInt(etProductQuantity.getText().toString());
                String description = etProductDescription.getText().toString();

                Utils.updateProduct(id, new Product(0, name, price, quantity, description));
                Toast.makeText(AdminActivity.this, "Updated successfully", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    private void clearInput() {
        productId = null;
        etProductName.setText("");
        etProductPrice.setText("");
        etProductQuantity.setText("");
        etProductDescription.setText("");
    }

    private void loadData() {
        productList.clear();
//        productList = Utils.loadProduct();
        for (Product product : Utils.loadProduct()) {
            productList.add(product);
        }
//        productAdapter.notifyDataSetChanged();
        productRecycleAdapter.notifyDataSetChanged();
    }
}