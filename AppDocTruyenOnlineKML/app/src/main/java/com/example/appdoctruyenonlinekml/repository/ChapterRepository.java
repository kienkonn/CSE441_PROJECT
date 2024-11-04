package com.example.appdoctruyenonlinekml.repository;

import androidx.annotation.NonNull;
import com.example.appdoctruyenonlinekml.model.Chapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChapterRepository {
    private final DatabaseReference databaseReference;

    public ChapterRepository() {
        databaseReference = FirebaseDatabase.getInstance().getReference("books");
    }

    public void getChapter(String bookId, String chapterId, OnChapterLoadedCallback callback) {
        databaseReference.child(bookId).child("chapters").child(chapterId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Chapter chapter = snapshot.getValue(Chapter.class);
                        if (chapter != null) {
                            callback.onChapterLoaded(chapter);
                        } else {
                            callback.onError(new Exception("Chương không tồn tại"));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        callback.onError(error.toException());
                    }
                });
    }

    public void getChapterByNumber(String bookId, int chapterNumber, OnChapterLoadedCallback callback) {
        databaseReference.child(bookId).child("chapters")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot chapterSnapshot : snapshot.getChildren()) {
                            Integer number = chapterSnapshot.child("chapterNumber").getValue(Integer.class);
                            if (number != null && number == chapterNumber) {
                                Chapter chapter = chapterSnapshot.getValue(Chapter.class);
                                if (chapter != null) {
                                    callback.onChapterLoaded(chapter);
                                    return; // Kết thúc vòng lặp nếu tìm thấy chương
                                }
                            }
                        }
                        callback.onError(new Exception("Chương không tồn tại với số thứ tự: " + chapterNumber));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        callback.onError(error.toException());
                    }
                });
    }

    // Thêm phương thức lấy danh sách chương
    public void getChapters(String bookId, OnChaptersLoadedCallback callback) {
        databaseReference.child(bookId).child("chapters")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Chapter> chapters = new ArrayList<>();
                        for (DataSnapshot chapterSnapshot : snapshot.getChildren()) {
                            Chapter chapter = chapterSnapshot.getValue(Chapter.class);
                            if (chapter != null) {
                                chapters.add(chapter);
                            }
                        }
                        callback.onChaptersLoaded(chapters);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        callback.onError(error.toException());
                    }
                });
    }

    public interface OnChapterLoadedCallback {
        void onChapterLoaded(Chapter chapter);
        void onError(Exception e);
    }

    // Thêm interface cho danh sách chương
    public interface OnChaptersLoadedCallback {
        void onChaptersLoaded(List<Chapter> chapters);
        void onError(Exception e);
    }
}
