package com.example.appdoctruyenonlinekml.repository;

import android.util.Log;
import com.example.appdoctruyenonlinekml.model.Book;
import com.example.appdoctruyenonlinekml.model.Chapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private final DatabaseReference databaseReference;

    public BookRepository() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void getBooks(OnBooksLoadedCallback callback) {
        databaseReference.child("books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Book> bookList = new ArrayList<>();
                for (DataSnapshot bookSnapshot : snapshot.getChildren()) {
                    Book book = bookSnapshot.getValue(Book.class);
                    if (book != null) {
                        bookList.add(book);
                    }
                }
                Log.d("BookRepository", "Books retrieved: " + bookList.size());
                callback.onBooksLoaded(bookList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("BookRepository", "Error retrieving books: " + error.getMessage());
                callback.onError(error.toException());
            }
        });
    }

    public void getChapters(String bookId, OnChaptersLoadedCallback callback) {
        databaseReference.child("books").child(bookId).child("chapters").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Chapter> chapterList = new ArrayList<>();
                for (DataSnapshot chapterSnapshot : snapshot.getChildren()) {
                    Chapter chapter = chapterSnapshot.getValue(Chapter.class);
                    if (chapter != null) {
                        chapterList.add(chapter);
                    }
                }
                Log.d("BookRepository", "Chapters retrieved: " + chapterList.size());
                callback.onChaptersLoaded(chapterList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("BookRepository", "Error retrieving chapters: " + error.getMessage());
                callback.onError(error.toException());
            }
        });
    }

    public void getAuthorName(String authorId, OnAuthorLoadedCallback callback) {
        databaseReference.child("authors").child(authorId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String authorName = snapshot.child("name").getValue(String.class);
                if (authorName != null) {
                    callback.onAuthorLoaded(authorName);
                } else {
                    callback.onError(new Exception("Tên tác giả không tìm thấy"));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                callback.onError(error.toException());
            }
        });
    }

    // Cập nhật BookRepository với phương thức lấy Book theo ID
    public void getBookById(String bookId, OnBookLoadedCallback callback) {
        databaseReference.child("books").child(bookId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Book book = snapshot.getValue(Book.class);
                if (book != null) {
                    callback.onBookLoaded(book);
                } else {
                    callback.onError(new Exception("Sách không tìm thấy"));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("BookRepository", "Error retrieving book: " + error.getMessage());
                callback.onError(error.toException());
            }
        });
    }

    // Thêm phương thức để lấy chương theo ID
    public void getChapterById(String bookId, String chapterId, OnChapterLoadedCallback callback) {
        databaseReference.child("books").child(bookId).child("chapters").child(chapterId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Chapter chapter = snapshot.getValue(Chapter.class);
                if (chapter != null) {
                    callback.onChapterLoaded(chapter);
                } else {
                    callback.onError(new Exception("Chương không tìm thấy"));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("BookRepository", "Error retrieving chapter: " + error.getMessage());
                callback.onError(error.toException());
            }
        });
    }

    // Thêm phương thức để thêm sách vào thư viện
    public void addBookToLibrary(String userId, String bookId, OnBookAddedCallback callback) {
        databaseReference.child("users").child(userId).child("library").child(bookId).setValue(true)
                .addOnSuccessListener(aVoid -> {
                    Log.d("BookRepository", "Book added to library: " + bookId);
                    callback.onBookAdded();
                })
                .addOnFailureListener(e -> {
                    Log.e("BookRepository", "Error adding book to library: " + e.getMessage());
                    callback.onError(e);
                });
    }

    // Thêm phương thức để xóa sách khỏi lịch sử
    public void removeBookFromHistory(String userId, String bookId, OnBookRemovedCallback callback) {
        databaseReference.child("users").child(userId).child("history").child(bookId).removeValue()
                .addOnSuccessListener(aVoid -> {
                    Log.d("BookRepository", "Book removed from history: " + bookId);
                    callback.onBookRemoved();
                })
                .addOnFailureListener(e -> {
                    Log.e("BookRepository", "Error removing book from history: " + e.getMessage());
                    callback.onError(e);
                });
    }

    public interface OnChapterLoadedCallback {
        void onChapterLoaded(Chapter chapter);
        void onError(Exception e);
    }

    public interface OnChaptersLoadedCallback {
        void onChaptersLoaded(List<Chapter> chapterList);
        void onError(Exception e);
    }

    public interface OnAuthorLoadedCallback {
        void onAuthorLoaded(String authorName);
        void onError(Exception e);
    }

    public interface OnBooksLoadedCallback {
        void onBooksLoaded(List<Book> bookList);
        void onError(Exception e);
    }

    public interface OnBookAddedCallback {
        void onBookAdded();
        void onError(Exception e);
    }

    public interface OnBookRemovedCallback {
        void onBookRemoved();
        void onError(Exception e);
    }

    public interface OnBookLoadedCallback {
        void onBookLoaded(Book book);
        void onError(Exception e);
    }
}
