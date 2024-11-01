package com.example.appdoctruyenonlinekml;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.appdoctruyenonlinekml.view.fragment.HistoryFragment;
import com.example.appdoctruyenonlinekml.view.fragment.HomeFragment;
import com.example.appdoctruyenonlinekml.view.fragment.ProfileFragment;
import com.example.appdoctruyenonlinekml.view.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {
    private ImageButton btnHistory, btnHome, btnSearch, btnProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các nút điều hướng
        btnHistory = findViewById(R.id.btnHistory);
        btnHome = findViewById(R.id.btnHome);
        btnSearch = findViewById(R.id.btnSearch);
        btnProfile = findViewById(R.id.btnProfile);

        // Hiển thị ReadingFragment mặc định khi mở ứng dụng
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
            setActiveButton(btnHome); // Đánh dấu nút Home là đã chọn
        }

        // Cài đặt sự kiện click cho các nút
        setupButtonClickListeners();
    }

    private void setupButtonClickListeners() {
        btnHome.setOnClickListener(v -> {
            loadFragment(new HomeFragment());
            setActiveButton(btnHome);
        });

        btnHistory.setOnClickListener(v -> {
            loadFragment(new HistoryFragment());
            setActiveButton(btnHistory);
        });

        btnSearch.setOnClickListener(v -> {
            loadFragment(new SearchFragment());
            setActiveButton(btnSearch);
        });

        btnProfile.setOnClickListener(v -> {
            loadFragment(new ProfileFragment());
            setActiveButton(btnProfile);
        });
    }

    // Phương thức để load Fragment
    private void loadFragment(Fragment fragment) {
        if (findViewById(R.id.fragment_container) != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null) // Thêm vào back stack để có thể quay lại
                    .commit();
        }
    }

    // Phương thức để thay đổi trạng thái của nút
    private void setActiveButton(ImageButton activeButton) {
        resetButtonStates(); // Đặt lại trạng thái của tất cả các nút
        activeButton.setColorFilter(Color.YELLOW); // Thay đổi màu của nút đang được chọn
    }

    // Phương thức để reset trạng thái của tất cả các nút
    private void resetButtonStates() {
        btnHome.clearColorFilter();
        btnHistory.clearColorFilter();
        btnSearch.clearColorFilter();
        btnProfile.clearColorFilter();
    }
}
