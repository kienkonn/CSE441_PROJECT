package com.example.appdoctruyenonlinekml;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityLoginProfile extends AppCompatActivity {

    private FirebaseAuth mAuth; // Đối tượng FirebaseAuth
    private TextView emailTextView; // Tham chiếu đến TextView để hiển thị email

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_profile);

        // Khởi tạo FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Tham chiếu đến TextView email
        emailTextView = findViewById(R.id.email);

        // Lấy người dùng hiện tại từ Firebase
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // Hiển thị email của người dùng
        if (currentUser != null) {
            String userEmail = currentUser.getEmail();
            emailTextView.setText(userEmail);
        }

        LinearLayout logoutOption = findViewById(R.id.logoutOption);

        logoutOption.setOnClickListener(view -> performLogout());
    }

    private void performLogout() {
        // Đăng xuất khỏi Firebase
        if (mAuth.getCurrentUser() != null) {
            mAuth.signOut();
        }

        // Xóa thông tin đăng nhập từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Xóa tất cả dữ liệu đã lưu
        editor.apply();

        Intent intent = new Intent(this, ActivityLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
