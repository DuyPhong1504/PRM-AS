package com.example.as.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.as.HomeActivity;
import com.example.as.Utils;
import com.example.as.database.Database;
import com.example.as.R;
import com.example.as.model.Users;
import com.google.android.material.textfield.TextInputEditText;

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
        Utils.getDd(this);
        txtUsername = (TextInputEditText) findViewById(R.id.txtUsername);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        register = (TextView) findViewById(R.id.txtRegister);
        error = (TextView) findViewById(R.id.txtLoginError);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();
                Users login = Utils.login(username, password);
                if(login != null ){
                    if(login.getRole().getText().equalsIgnoreCase("admin")){
                        Intent intent = new Intent(getBaseContext(), HomeActivity.class);

                        intent.putExtra("userId", login.getId());
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                } else {
                    error.setText("username or password wrong");
                }
            }
        });
    }
}