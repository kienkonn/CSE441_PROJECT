package com.example.appdoctruyenonlinekml.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

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
                return new LibraryFragment(); // Fragment cho Tủ sách
            case 1:
                return new HistoryFragment(); // Fragment cho Lịch sử đọc
            default:
                return new LibraryFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Có 2 tab
    }
}
