package com.example.appdoctruyenonlinekml.model;

import java.io.Serializable;
import java.util.List;

public class Author implements Serializable {
    private String authorID;
    private String name;
    private List<String> books;

    public Author(){

    }

    public Author(String authorID, String name, List<String> books) {
        this.authorID = authorID;
        this.name = name;
        this.books = books;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }
}