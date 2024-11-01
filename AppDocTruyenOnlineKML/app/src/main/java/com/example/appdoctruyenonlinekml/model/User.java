package com.example.appdoctruyenonlinekml.model;

import java.util.List;

public class User {
    private String userId;
    private String name;
    private String email;
    private List<String> readingList;
//    private List<History> history;
    private List<String> favorites;

//    public User(String userId, String name, String email, List<String> readingList, List<History> history, List<String> favorites) {
    public User(String userId, String name, String email, List<String> readingList, List<String> favorites) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.readingList = readingList;
//        this.history = history;
        this.favorites = favorites;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getReadingList() {
        return readingList;
    }

    public void setReadingList(List<String> readingList) {
        this.readingList = readingList;
    }
//
//    public List<History> getHistory() {
//        return history;
//    }
//
//    public void setHistory(List<History> history) {
//        this.history = history;
//    }

    public List<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<String> favorites) {
        this.favorites = favorites;
    }
}
