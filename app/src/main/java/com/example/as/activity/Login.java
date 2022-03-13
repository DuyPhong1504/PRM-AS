package com.example.as;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
    Database database;
    TextInputEditText txtUsername, txtPassword;
    Button btnLogin;
    String username, password;
    TextView error, register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = (TextInputEditText) findViewById(R.id.txtUsername);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        register = (TextView) findViewById(R.id.txtRegister);
        error = (TextView) findViewById(R.id.txtLoginError);


        database = new Database(this, "GhiChu.sqlite", null, 1);
        database.QueryData("Create table if not exists User(id Integer Primary Key Autoincrement," +
                "username nvarchar(200),password nvarchar(200))");

//        database.QueryData("Insert into User values(null,'User','123456')");

        Intent i = new Intent(this, MainActivity.class);

        Intent iregis = new Intent(this, Register.class);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();
                Cursor dataUser = database.GetData("Select * from User " +
                        "where username like '" + username + "' and " +
                        "password like '" + password + "' ;");
                if (dataUser.getCount() > 0) {
                    startActivity(i);
                } else {
                    error.setText("username or password wrong");
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(iregis);
            }
        });


    }
}