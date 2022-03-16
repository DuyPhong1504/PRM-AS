package com.example.as.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.as.database.Database;
import com.example.as.R;
import com.example.as.database.EntityDatabase;
import com.example.as.model.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class Register extends AppCompatActivity {
    Database database;
    TextInputEditText txtUsername, txtPassword, txtPassword2;
    Button btnRegister;
    String username, password, password2;
    TextView error, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        txtUsername = (TextInputEditText) findViewById(R.id.txtUsernameRegis);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPasswordRegis);
        txtPassword2 = (TextInputEditText) findViewById(R.id.txtPassword2Regis);
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
                password2 = txtPassword2.getText().toString();

                if (!password.equals(password2)) {
                    error.setText("Password is not matched");
                    return;
                }

//                database.QueryData("Insert into User values(null,'"+username+"','"+password+"')");
                User user = new User(username, password);

                if (isUserExisted(user.getUsername())) {
                    error.setText("Username existed");
                    return;
                }

                EntityDatabase.getInstance(getApplicationContext()).getUserDao().insert(user);
//                Cursor dataUser = database.GetData("Select * from User " +
//                        "where username like '" + username + "' and " +
//                        "password like '" + password + "' ;");
//                if (dataUser.getCount() > 0) {
//                    error.setText("regis success");
//                    txtUsername.setText("");
//                    txtPassword.setText("");
//                    txtPassword2.setText("");
//                    error.requestFocus();
//                } else {
//                    Toast.makeText(Register.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
//                }
                List<User> results = EntityDatabase.getInstance(getApplicationContext()).getUserDao().getAll();
                if (results != null && results.size() >= 1) {
                    System.out.println(results);
                    Toast.makeText(Register.this, "Register successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Register.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
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

    private boolean isUserExisted(String username) {
        List<User> results = EntityDatabase.getInstance(getApplicationContext()).getUserDao().checkExisted(username);
        return results != null && !results.isEmpty();
    }
}