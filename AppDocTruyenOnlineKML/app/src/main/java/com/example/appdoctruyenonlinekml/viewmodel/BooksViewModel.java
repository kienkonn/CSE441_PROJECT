package com.example.appdoctruyenonlinekml.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appdoctruyenonlinekml.model.Book;
import com.example.appdoctruyenonlinekml.model.Chapter;
import com.example.appdoctruyenonlinekml.model.User;
import com.example.appdoctruyenonlinekml.repository.BookRepository;
import com.example.appdoctruyenonlinekml.repository.UserRepository;

import java.util.List;

public class BooksViewModel extends ViewModel {
    private final MutableLiveData<List<Book>> booksLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Chapter>> chaptersLiveData = new MutableLiveData<>();
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>(); // Thêm MutableLiveData cho User
    private final BookRepository repository;
    private final UserRepository userRepository;

    public BooksViewModel() {
        repository = new BookRepository();
        userRepository = new UserRepository();
        fetchBooks();  // Gọi để tải danh sách sách ngay khi ViewModel được khởi tạo
    }

    // Phương thức để tải danh sách sách
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

    // Getter cho danh sách sách
    public LiveData<List<Book>> getBooks() {
        return booksLiveData;
    }

    // Getter cho danh sách chương
    public LiveData<List<Chapter>> getChaptersLiveData() {
        return chaptersLiveData;
    }

    // Phương thức để tải chương của sách theo ID
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

    // Phương thức để thêm sách vào tủ sách của người dùng
    public void addBookToLibrary(Book book, String userId) {
        userRepository.addBookToLibrary(userId, book.getBookId(), new UserRepository.OnBookAddedCallback() {
            @Override
            public void onBookAdded() {
                Log.d("BooksViewModel", "Book added to library: " + book.getTitle());
            }

            @Override
            public void onError(Exception e) {
                Log.e("BooksViewModel", "Error adding book to library: " + e.getMessage());
            }
        });
    }

    // Phương thức để tải thông tin người dùng
    public void loadUserData(String userId) {
        userRepository.getUserData(userId, new UserRepository.OnUserDataLoadedCallback() {
            @Override
            public void onUserDataLoaded(User user) {
                userLiveData.setValue(user);
            }

            @Override
            public void onError(Exception e) {
                Log.e("BooksViewModel", "Error loading user data: " + e.getMessage());
                userLiveData.setValue(null);
            }
        });
    }

    // Getter cho thông tin người dùng
    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }
}
