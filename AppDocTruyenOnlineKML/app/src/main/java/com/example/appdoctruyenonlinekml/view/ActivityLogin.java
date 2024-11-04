package com.example.appdoctruyenonlinekml.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyenonlinekml.MainActivity;
import com.example.appdoctruyenonlinekml.R;
import com.example.appdoctruyenonlinekml.view.fragment.ActivityRegister;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityLogin extends AppCompatActivity {
    private FirebaseAuth auth;

    EditText username;
    EditText password;
    Button loginButton;
    TextView txtgoR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.edt_email);
        password = findViewById(R.id.edt_password);
        txtgoR = findViewById(R.id.txt_goR);

        txtgoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(intent);
            }
        });

        loginButton = findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(!user.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(user).matches()){
                    if(!pass.isEmpty()) {
                        auth.signInWithEmailAndPassword(user, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(ActivityLogin.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                                intent.putExtra("openHomeFragment", true); // Truyền dữ liệu để mở HomeFragment
                                startActivity(intent);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ActivityLogin.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        password.setError("Mật khẩu không được bỏ trống!");
                    }
                } else if(user.isEmpty()){
                    username.setError("Email không được bỏ trống");
                } else {
                    username.setError("Vui lòng nhập email hợp lệ!");
                }
            }
        });
    }
}
