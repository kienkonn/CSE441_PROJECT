package com.example.appdoctruyenonlinekml.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appdoctruyenonlinekml.model.Chapter;
import com.example.appdoctruyenonlinekml.repository.ChapterRepository;

import java.util.List;

public class ChapterViewModel extends ViewModel {
    private final ChapterRepository chapterRepository;
    private final MutableLiveData<Chapter> chapterLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Chapter>> chaptersLiveData = new MutableLiveData<>(); // Thêm LiveData cho danh sách chương
    private final MutableLiveData<Exception> errorLiveData = new MutableLiveData<>();

    public ChapterViewModel() {
        chapterRepository = new ChapterRepository();
    }

    public LiveData<Chapter> getChapter(String bookId, String chapterId) {
        chapterRepository.getChapter(bookId, chapterId, new ChapterRepository.OnChapterLoadedCallback() {
            @Override
            public void onChapterLoaded(Chapter chapter) {
                chapterLiveData.setValue(chapter);
            }

            @Override
            public void onError(Exception e) {
                errorLiveData.setValue(e);
            }
        });
        return chapterLiveData;
    }

    public LiveData<Chapter> getChapterByNumber(String bookId, int chapterNumber) {
        chapterRepository.getChapterByNumber(bookId, chapterNumber, new ChapterRepository.OnChapterLoadedCallback() {
            @Override
            public void onChapterLoaded(Chapter chapter) {
                chapterLiveData.setValue(chapter);
            }

            @Override
            public void onError(Exception e) {
                errorLiveData.setValue(e);
            }
        });
        return chapterLiveData;
    }

    // Thêm phương thức lấy danh sách chương
    public LiveData<List<Chapter>> getChapters(String bookId) {
        chapterRepository.getChapters(bookId, new ChapterRepository.OnChaptersLoadedCallback() {
            @Override
            public void onChaptersLoaded(List<Chapter> chapters) {
                chaptersLiveData.setValue(chapters);
            }

            @Override
            public void onError(Exception e) {
                errorLiveData.setValue(e);
            }
        });
        return chaptersLiveData; // Trả về danh sách chương
    }

    public LiveData<Exception> getError() {
        return errorLiveData;
    }
}
