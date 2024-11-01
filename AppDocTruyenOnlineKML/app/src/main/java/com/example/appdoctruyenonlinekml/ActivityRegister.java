package com.example.appdoctruyenonlinekml;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class ActivityRegister extends AppCompatActivity {
    private FirebaseAuth auth;
    EditText edtUser, edtPwd, edtRepwd;
    Button btnRegister;
    TextView txtGoToLogin;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
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
        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String user, pwd, rePwd;
                user = edtUser.getText().toString().trim();
                pwd = edtPwd.getText().toString().trim();
                rePwd = edtRepwd.getText().toString().trim();
                if (user.equals("") || pwd.equals("") || rePwd.equals("")) {
                    Toast.makeText(ActivityRegister.this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                }else if(!pwd.equals(rePwd)){
                    Toast.makeText(ActivityRegister.this, "Mật khẩu không trùng khớp. Vui lòng nhập lại!", Toast.LENGTH_SHORT).show();

                } else {
                    auth.createUserWithEmailAndPassword(user, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ActivityRegister.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ActivityRegister.this, ActivityLogin.class));
                            } else {
                                Toast.makeText(ActivityRegister.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
