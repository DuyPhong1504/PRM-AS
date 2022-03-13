package com.example.as;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {
    Database database;
    TextInputEditText txtUsername, txtPassword;
    Button btnRegister;
    String username, password;
    TextView error, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        txtUsername = (TextInputEditText) findViewById(R.id.txtUsernameRegis);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPasswordRegis);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        register = (TextView) findViewById(R.id.txtBacktoLogin);
        error = (TextView) findViewById(R.id.txtRegisterError);


        database = new Database(this, "GhiChu.sqlite", null, 1);
        database.QueryData("Create table if not exists User(id Integer Primary Key Autoincrement," +
                "username nvarchar(200),password nvarchar(200))");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();
                database.QueryData("Insert into User values(null,'"+username+"','"+password+"')");

                Cursor dataUser = database.GetData("Select * from User " +
                        "where username like '" + username + "' and " +
                        "password like '" + password + "' ;");
                if (dataUser.getCount() > 0) {
                    error.setText("regis success");
                    txtUsername.setText("");
                    txtPassword.setText("");
                    error.requestFocus();
                } else {
                    error.setText("username or password wrong");
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}