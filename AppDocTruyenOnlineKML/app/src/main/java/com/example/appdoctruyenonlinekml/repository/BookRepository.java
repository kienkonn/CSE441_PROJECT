package com.example.appdoctruyenonlinekml.repository;

import androidx.annotation.NonNull;

import com.example.appdoctruyenonlinekml.model.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private final DatabaseReference bookRef;

    public BookRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        bookRef = database.getReference("books");
    }

    public interface OnBooksLoadedCallback {
        void onBooksLoaded(List<Book> bookList);
    }

    public void getBooks(OnBooksLoadedCallback callback) {
        bookRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Book> bookList = new ArrayList<>();
                for (DataSnapshot bookSnapshot : snapshot.getChildren()) {
                    Book book = bookSnapshot.getValue(Book.class);
                    bookList.add(book);
                }
                callback.onBooksLoaded(bookList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu cần
            }
        });
    }
}
