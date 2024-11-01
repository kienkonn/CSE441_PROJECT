package com.example.appdoctruyenonlinekml.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appdoctruyenonlinekml.model.Book;
import com.example.appdoctruyenonlinekml.repository.BookRepository;

import java.util.List;

public class

BooksViewModel extends ViewModel {
    private final MutableLiveData<List<Book>> books = new MutableLiveData<>();
    private final BookRepository repository;

    public BooksViewModel() {
        repository = new BookRepository();
        fetchBooks();
    }

    private void fetchBooks() {
        repository.getBooks(new BookRepository.OnBooksLoadedCallback() {
            @Override
            public void onBooksLoaded(List<Book> bookList) {
                books.setValue(bookList);
            }
        });
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }
}
