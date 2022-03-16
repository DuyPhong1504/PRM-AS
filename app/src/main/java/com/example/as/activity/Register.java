package com.example.as.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.as.HomeActivity;
import com.example.as.Utils;
import com.example.as.database.Database;
import com.example.as.R;
import com.example.as.model.Users;
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
        btnRegister.setOnClickListener(view -> {
            username = txtUsername.getText().toString();
            password = txtPassword.getText().toString();
            if (username.trim().isEmpty()) {
                Toast.makeText(this, "Username is required", Toast.LENGTH_LONG);
            }
            if (password.trim().isEmpty()) {
                Toast.makeText(this, "Password is required", Toast.LENGTH_LONG);
            }

            Users register = Utils.register(username, password);
            if(register == null){
                error.setText(username + " is used by other");
            }else {
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
            }
        });
    }
}