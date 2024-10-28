package com.example.appdoctruyenonlinekml;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityLogin extends AppCompatActivity {
    DBHelper dbHelper;
    EditText username;
    EditText password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DBHelper(this);
        username = findViewById(R.id.edt_email);
        password = findViewById(R.id.edt_password);
        loginButton = findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isLoggedId = dbHelper.checkUser(username.getText().toString(), password.getText().toString());
                if (isLoggedId){
                    Intent intent = new Intent(ActivityLogin.this, ActivityRvProducts.class);
                    startActivity(intent);
                }else
                    Toast.makeText(ActivityLogin.this, "Đăng nhập thất bại!", Toast.LENGTH_LONG).show();
//                if (username.getText().toString().equals("kienkonn@gmail.com") && password.getText().toString().equals("1234")) {
//                    Toast.makeText(ActivityLogin.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(ActivityLogin.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }
}