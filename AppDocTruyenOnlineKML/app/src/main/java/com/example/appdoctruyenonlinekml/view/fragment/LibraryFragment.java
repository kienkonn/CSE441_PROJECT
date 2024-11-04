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
import com.example.appdoctruyenonlinekml.model.User;
import com.example.appdoctruyenonlinekml.repository.UserRepository;

public class LibraryFragment extends Fragment {
    private UserRepository userRepository;
    private TextView textViewFavorites; // TextView để hiển thị sách yêu thích

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        textViewFavorites = view.findViewById(R.id.tvLibraryTitle);

        // Khởi tạo UserRepository và lấy dữ liệu người dùng
        userRepository = new UserRepository();
        String userId = "userID1"; // Thay bằng ID người dùng thực tế

        userRepository.getUserData(userId, new UserRepository.OnUserDataLoadedCallback() {
            @Override
            public void onUserDataLoaded(User user) {
                // Hiển thị sách yêu thích
                StringBuilder favorites = new StringBuilder("Sách yêu thích:\n");
                for (String bookId : user.getFavoriteBooks()) {
                    favorites.append("Sách ID: ").append(bookId).append("\n");
                }
                textViewFavorites.setText(favorites.toString());
            }

            @Override
            public void onError(Exception e) {
                textViewFavorites.setText("Không thể tải sách yêu thích.");
            }
        });

        return view;
    }
}
