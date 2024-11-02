package com.example.appdoctruyenonlinekml.model;

import java.io.Serializable;

public class Chapter implements Serializable {
    private String chapterID;
    private int chapterNumber;
    private String content;
    private String title;

    public Chapter() {
        // Constructor mặc định cho Firebase
    }

    // Constructor với tham số
    public Chapter(String chapterID, int chapterNumber, String content, String title) {
        this.chapterID = chapterID;
        this.chapterNumber = chapterNumber;
        this.content = content;
        this.title = title;
    }

    // Getter và Setter
    public String getChapterID() {
        return chapterID;
    }

    public void setChapterID(String chapterID) {
        this.chapterID = chapterID;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

