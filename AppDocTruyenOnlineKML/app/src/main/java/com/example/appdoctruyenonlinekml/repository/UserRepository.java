package com.example.appdoctruyenonlinekml.repository;

import com.example.appdoctruyenonlinekml.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;

    public UserRepository() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance(); // Khởi tạo FirebaseAuth
    }

    // Lấy ID người dùng hiện tại
    public String getCurrentUserId() {
        return auth.getCurrentUser() != null ? auth.getCurrentUser().getUid() : null;
    }

    public void getUserData(String userId, OnUserDataLoadedCallback callback) {
        databaseReference.child("users").child(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            String username = dataSnapshot.child("username").getValue(String.class);
                            String email = dataSnapshot.child("email").getValue(String.class);

                            List<String> favoriteBooks = new ArrayList<>();
                            for (DataSnapshot bookSnapshot : dataSnapshot.child("favoriteBooks").getChildren()) {
                                favoriteBooks.add(bookSnapshot.getValue(String.class));
                            }

                            List<User.ReadingHistory> readingHistory = new ArrayList<>();
                            for (DataSnapshot historySnapshot : dataSnapshot.child("readingHistory").getChildren()) {
                                String bookID = historySnapshot.child("bookID").getValue(String.class);
                                int lastReadChapter = historySnapshot.child("lastReadChapter").getValue(Integer.class);
                                int lastReadPosition = historySnapshot.child("lastReadPosition").getValue(Integer.class);
                                readingHistory.add(new User.ReadingHistory(bookID, lastReadChapter, lastReadPosition));
                            }

                            User user = new User(userId, username, email, favoriteBooks, readingHistory);
                            callback.onUserDataLoaded(user);
                        } else {
                            callback.onError(new Exception("User not found"));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        callback.onError(databaseError.toException());
                    }
                });
    }

    public void addBookToLibrary(String userId, String bookId, OnBookAddedCallback callback) {
        DatabaseReference userRef = databaseReference.child("users").child(userId).child("favoriteBooks").child(bookId);
        userRef.setValue(bookId)  // Thêm bookId vào danh sách yêu thích
                .addOnSuccessListener(aVoid -> {
                    callback.onBookAdded(); // Gọi callback khi thêm thành công
                })
                .addOnFailureListener(e -> {
                    callback.onError(new Exception("Error adding book to library: " + e.getMessage()));
                });
    }

    public interface OnUserDataLoadedCallback {
        void onUserDataLoaded(User user);
        void onError(Exception e);
    }

    public interface OnBookAddedCallback {
        void onBookAdded();
        void onError(Exception e);
    }
}
