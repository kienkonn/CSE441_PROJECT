package com.example.appdoctruyenonlinekml.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appdoctruyenonlinekml.R;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate layout cho fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Tạo TextView để hiển thị thông điệp
        TextView textView = view.findViewById(R.id.tvProfile);
        textView.setText("Trang cá nhân");

        return view;
    }
}
