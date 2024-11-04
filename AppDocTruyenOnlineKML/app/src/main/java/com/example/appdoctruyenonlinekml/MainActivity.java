package com.example.appdoctruyenonlinekml;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.appdoctruyenonlinekml.view.fragment.FragmentHistory;
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

        // Kiểm tra Intent để mở HomeFragment nếu cần
        if (getIntent().getBooleanExtra("openHomeFragment", false)) {
            loadFragment(new HomeFragment());
            setActiveButton(btnHome);
        } else if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
            setActiveButton(btnHome);
        } else {
            setButtonStateFromCurrentFragment();
        }

        // Cài đặt sự kiện click cho các nút
        setupButtonClickListeners();
    }

    private void setupButtonClickListeners() {
        btnHome.setOnClickListener(v -> onNavButtonClick(new HomeFragment(), btnHome));
        btnHistory.setOnClickListener(v -> onNavButtonClick(new FragmentHistory(), btnHistory));
        btnSearch.setOnClickListener(v -> onNavButtonClick(new SearchFragment(), btnSearch));
        btnProfile.setOnClickListener(v -> onNavButtonClick(new ProfileFragment(), btnProfile));
    }

    private void onNavButtonClick(Fragment fragment, ImageButton button) {
        loadFragment(fragment);
        setActiveButton(button);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void setActiveButton(ImageButton activeButton) {
        resetButtonStates();
        activeButton.setColorFilter(Color.YELLOW);
    }

    private void resetButtonStates() {
        btnHome.clearColorFilter();
        btnHistory.clearColorFilter();
        btnSearch.clearColorFilter();
        btnProfile.clearColorFilter();
    }

    private void setButtonStateFromCurrentFragment() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentFragment instanceof HomeFragment) {
            setActiveButton(btnHome);
        } else if (currentFragment instanceof FragmentHistory) {
            setActiveButton(btnHistory);
        } else if (currentFragment instanceof SearchFragment) {
            setActiveButton(btnSearch);
        } else if (currentFragment instanceof ProfileFragment) {
            setActiveButton(btnProfile);
        }
    }
}
