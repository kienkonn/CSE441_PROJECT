package com.example.appdoctruyenonlinekml.model;

public class Chapter {
    private String chapterId;
    private int chapterNumber;
    private String content;

    public Chapter() {
    }

    public Chapter(String chapterId, int chapterNumber, String content) {
        this.chapterId = chapterId;
        this.chapterNumber = chapterNumber;
        this.content = content;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
