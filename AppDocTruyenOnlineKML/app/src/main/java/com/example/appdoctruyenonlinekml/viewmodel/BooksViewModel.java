package com.example.appdoctruyenonlinekml.viewmodel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appdoctruyenonlinekml.model.Book;
import com.example.appdoctruyenonlinekml.repository.FirebaseRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BooksViewModel extends ViewModel {
    private final MutableLiveData<List<Book>> books = new MutableLiveData<>();
    private final FirebaseRepository repository;

    public BooksViewModel() {
        repository = new FirebaseRepository();
        fetchBooks();
    }

    private void fetchBooks() {
        repository.getBooks(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Book> bookList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Book book = dataSnapshot.getValue(Book.class);
                    bookList.add(book);
                }
                books.setValue(bookList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle database error if needed
            }
        });
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }
}

