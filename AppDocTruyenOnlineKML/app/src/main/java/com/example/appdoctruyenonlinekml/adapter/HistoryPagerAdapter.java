package com.example.appdoctruyenonlinekml.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appdoctruyenonlinekml.view.fragment.FragmentHistory;
import com.example.appdoctruyenonlinekml.view.fragment.HistoryFragment;
import com.example.appdoctruyenonlinekml.view.fragment.LibraryFragment;

public class HistoryPagerAdapter extends FragmentStateAdapter {

    public HistoryPagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LibraryFragment(); // Fragment cho thư viện
            case 1:
                return new HistoryFragment(); // Fragment cho lịch sử
            default:
                return new LibraryFragment(); // Mặc định
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Số lượng tab
    }
}
