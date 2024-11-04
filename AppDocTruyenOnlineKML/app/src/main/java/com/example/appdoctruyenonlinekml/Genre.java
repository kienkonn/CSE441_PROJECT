package com.example.appdoctruyenonlinekml;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Genre implements Parcelable {
    private String name;
    private List<String> bookIds;

    public Genre(String name, List<String> bookIds) {
        this.name = name;
        this.bookIds = bookIds;
    }

    // Getter và Setter
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

    // Phương thức để viết đối tượng vào Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeStringList(bookIds);
    }

    // Phương thức để lấy kích thước của đối tượng
    @Override
    public int describeContents() {
        return 0;
    }

    // Tạo đối tượng từ Parcel
    protected Genre(Parcel in) {
        name = in.readString();
        bookIds = in.createStringArrayList();
    }

    // Tạo một Creator để khôi phục đối tượng từ Parcel
    public static final Creator<Genre> CREATOR = new Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };
}
