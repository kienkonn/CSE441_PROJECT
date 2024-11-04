package com.example.appdoctruyenonlinekml.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyenonlinekml.R;

public class ActivityLoginProfile extends AppCompatActivity {

    private TextView txt_Logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_profile); // Đảm bảo sử dụng đúng layout

        // Gán TextView cho nút "Đăng xuất"
        txt_Logout = findViewById(R.id.txt_logout);

        // Xử lý sự kiện nhấn "Đăng xuất"
        txt_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityLoginProfile.this, ActivityLogin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent); // Chuyển về trang đăng nhập
                finish(); // Đóng Activity hiện tại
            }
        });
    }
}
