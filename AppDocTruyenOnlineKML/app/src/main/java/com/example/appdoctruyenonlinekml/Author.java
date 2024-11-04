package com.example.appdoctruyenonlinekml;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Author implements Parcelable {
    private String authorID;
    private String name;
    private List<String> books;

    // Constructor rỗng
    public Author() {
    }

    // Constructor với tham số
    public Author(String authorID, String name, List<String> books) {
        this.authorID = authorID;
        this.name = name;
        this.books = books;
    }

    // Getter và Setter
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

    // Phương thức viết đối tượng vào Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(authorID);
        dest.writeString(name);
        dest.writeStringList(books);
    }

    // Phương thức để lấy kích thước của đối tượng
    @Override
    public int describeContents() {
        return 0;
    }

    // Tạo đối tượng từ Parcel
    protected Author(Parcel in) {
        authorID = in.readString();
        name = in.readString();
        books = in.createStringArrayList();
    }

    // Tạo một Creator để khôi phục đối tượng từ Parcel
    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
}