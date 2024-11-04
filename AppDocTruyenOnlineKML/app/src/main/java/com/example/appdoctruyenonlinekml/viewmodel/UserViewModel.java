package com.example.appdoctruyenonlinekml.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appdoctruyenonlinekml.model.User;
import com.example.appdoctruyenonlinekml.repository.UserRepository;

public class UserViewModel extends ViewModel {
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private final UserRepository userRepository;

    public UserViewModel() {
        userRepository = new UserRepository(); // Khởi tạo UserRepository
    }

    // Phương thức lấy ID người dùng hiện tại
    public String getCurrentUserId() {
        return userRepository.getCurrentUserId(); // Trả về ID người dùng hiện tại từ UserRepository
    }

    // Phương thức tải dữ liệu người dùng
    public void loadUserData() {
        String userId = getCurrentUserId(); // Lấy ID người dùng hiện tại
        if (userId != null) {
            userRepository.getUserData(userId, new UserRepository.OnUserDataLoadedCallback() {
                @Override
                public void onUserDataLoaded(User user) {
                    userLiveData.setValue(user); // Cập nhật LiveData khi tải thành công
                }

                @Override
                public void onError(Exception e) {
                    Log.e("UserViewModel", "Error loading user data: " + e.getMessage());
                    userLiveData.setValue(null); // Nếu có lỗi, đặt giá trị là null
                }
            });
        } else {
            Log.e("UserViewModel", "Current user ID is null");
            userLiveData.setValue(null); // Nếu không có ID, đặt giá trị là null
        }
    }

    // Phương thức để quan sát dữ liệu người dùng
    public LiveData<User> getUserLiveData() {
        return userLiveData; // Trả về LiveData để quan sát từ View
    }
}
