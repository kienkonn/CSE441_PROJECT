package com.example.appdoctruyenonlinekml.model;

import java.io.Serializable;
import java.util.List;

public class Genre implements Serializable {
    private String name;
    private List<String> bookIds;

    public Genre(String name, List<String> bookIds) {
        this.name = name;
        this.bookIds = bookIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<String> bookIds) {
        this.bookIds = bookIds;
    }
}
