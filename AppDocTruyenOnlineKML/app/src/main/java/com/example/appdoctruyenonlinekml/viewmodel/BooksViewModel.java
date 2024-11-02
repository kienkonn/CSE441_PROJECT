package com.example.appdoctruyenonlinekml.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appdoctruyenonlinekml.model.Book;
import com.example.appdoctruyenonlinekml.model.Chapter;
import com.example.appdoctruyenonlinekml.repository.BookRepository;

import java.util.List;

public class BooksViewModel extends ViewModel {
    private final MutableLiveData<List<Book>> booksLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Chapter>> chaptersLiveData = new MutableLiveData<>();
    private final BookRepository repository;

    public BooksViewModel() {
        repository = new BookRepository();
        fetchBooks();  // Gọi để tải danh sách sách ngay khi ViewModel được khởi tạo
    }

    private void fetchBooks() {
        repository.getBooks(new BookRepository.OnBooksLoadedCallback() {
            @Override
            public void onBooksLoaded(List<Book> bookList) {
                booksLiveData.setValue(bookList);
            }

            @Override
            public void onError(Exception e) {
                Log.e("BooksViewModel", "Error loading books: " + e.getMessage());
                booksLiveData.setValue(null);
            }
        });
    }

    public LiveData<List<Book>> getBooks() {
        return booksLiveData;
    }

    public LiveData<List<Chapter>> getChaptersLiveData() {
        return chaptersLiveData;
    }

    public void loadChapters(String bookId) {
        Log.d("BooksViewModel", "Loading chapters for book ID: " + bookId);
        repository.getChapters(bookId, new BookRepository.OnChaptersLoadedCallback() {
            @Override
            public void onChaptersLoaded(List<Chapter> chapterList) {
                Log.d("BooksViewModel", "Chapters loaded: " + chapterList.size());
                chaptersLiveData.setValue(chapterList);
            }

            @Override
            public void onError(Exception e) {
                Log.e("BooksViewModel", "Error loading chapters: " + e.getMessage());
                chaptersLiveData.setValue(null);
            }
        });
    }
}
