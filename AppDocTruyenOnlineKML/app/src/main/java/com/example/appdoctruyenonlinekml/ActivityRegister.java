package com.example.appdoctruyenonlinekml;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ActivityRegister extends AppCompatActivity {

    EditText edtUser, edtPwd, edtRepwd;
    Button btnRegister;
    TextView txtGoToLogin;
    DBHelper dbHelper;
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        edtUser = findViewById(R.id.emailEditText);
        edtPwd = findViewById(R.id.passwordEditText);
        edtRepwd = findViewById(R.id.confirmPasswordEditText);
        btnRegister = findViewById(R.id.registerButton);
        txtGoToLogin = findViewById(R.id.loginText);
        txtGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                startActivity(intent);
            }
        });
        dbHelper = new DBHelper(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String user, pwd, rePwd;
                user = edtUser.getText().toString();
                pwd = edtPwd.getText().toString();
                rePwd = edtRepwd.getText().toString();
                if (user.equals("") || pwd.equals("") || rePwd.equals("")) {
                    Toast.makeText(ActivityRegister.this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (pwd.equals(rePwd)){
                        if (dbHelper.checkUsername(user)){
                            Toast.makeText(ActivityRegister.this, "Tên người dùng đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                        boolean registeredSucces =  dbHelper.insertData(user,pwd);
                        if(registeredSucces)
                            Toast.makeText(ActivityRegister.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(ActivityRegister.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(ActivityRegister.this, "Mật khẩu không trùng khớp", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
    }
 }
