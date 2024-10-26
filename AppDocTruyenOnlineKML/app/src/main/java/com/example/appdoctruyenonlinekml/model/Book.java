package com.example.appdoctruyenonlinekml.model;

import java.util.List;
import java.util.Map;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String imageUrl;
    private double rating;
    private String status;

    public Book(String bookId, String title, String author, String imageUrl, String status, double rating, List<String> genres, Map<String, Chapter> chapters) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.imageUrl = imageUrl;
        this.status = status;
        this.rating = rating;
        this.genres = genres;
        this.chapters = chapters;
    }

    private List<String> genres;
    private Map<String, Chapter> chapters;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Map<String, Chapter> chapters) {
        this.chapters = chapters;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
