package com.example.appdoctruyenonlinekml;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book implements Parcelable {
    private String bookID; // ID của sách
    private String title; // Tiêu đề sách
    private String authorID; // ID của tác giả
    private String imageUrl; // Đường dẫn hình ảnh
    private double rating; // Đánh giá
    private String status; // Trạng thái sách
    private int views; // Số lượt xem
    private List<String> genres; // Danh sách thể loại
    private Map<String, Chapter> chapters; // Danh sách chương
    private String description; // Mô tả sách
    private String genreID; // ID thể loại

    public Book() {
        // Constructor mặc định
    }

    public Book(String bookId, String title, String authorId, String imageUrl, String status, double rating, List<String> genres, Map<String, Chapter> chapters, int views, String description, String genreId) {
        this.bookID = bookId;
        this.title = title;
        this.authorID = authorId;
        this.imageUrl = imageUrl;
        this.status = status;
        this.rating = rating;
        this.genres = genres;
        this.chapters = chapters;
        this.views = views;
        this.description = description;
        this.genreID = genreId;
    }

    // Getter và Setter cho các trường
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenreId() {
        return genreID;
    }

    public void setGenreId(String genreId) {
        this.genreID = genreId;
    }

    public String getBookId() {
        return bookID;
    }

    public void setBookId(String bookId) {
        this.bookID = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorId() {
        return authorID;
    }

    public void setAuthorId(String authorId) {
        this.authorID = authorId;
    }

    public String getImageUrl() {
        return imageUrl; // Getter cho imageUrl
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Map<String, Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Map<String, Chapter> chapters) {
        this.chapters = chapters;
    }

    // Phương thức để lấy nội dung của một chương theo ID
    public Chapter getChapterById(String chapterId) {
        return chapters != null ? chapters.get(chapterId) : null;
    }

    // Phương thức để lấy danh sách chương
    public List<Chapter> getAllChapters() {
        return chapters != null ? new ArrayList<>(chapters.values()) : new ArrayList<>();
    }

    // Phương thức để viết đối tượng vào Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookID);
        dest.writeString(title);
        dest.writeString(authorID);
        dest.writeString(imageUrl);
        dest.writeDouble(rating);
        dest.writeString(status);
        dest.writeInt(views);
        dest.writeStringList(genres);

        // Ghi lại các chương
        dest.writeInt(chapters != null ? chapters.size() : 0);
        if (chapters != null) {
            for (Map.Entry<String, Chapter> entry : chapters.entrySet()) {
                dest.writeString(entry.getKey());
                dest.writeParcelable(entry.getValue(), flags);
            }
        }

        dest.writeString(description);
        dest.writeString(genreID);
    }

    // Phương thức để lấy kích thước của đối tượng
    @Override
    public int describeContents() {
        return 0;
    }

    // Tạo đối tượng từ Parcel
    protected Book(Parcel in) {
        bookID = in.readString();
        title = in.readString();
        authorID = in.readString();
        imageUrl = in.readString();
        rating = in.readDouble();
        status = in.readString();
        views = in.readInt();
        genres = in.createStringArrayList();

        // Đọc lại các chương
        int chaptersSize = in.readInt();
        chapters = new HashMap<>();
        for (int i = 0; i < chaptersSize; i++) {
            String key = in.readString();
            Chapter chapter = in.readParcelable(Chapter.class.getClassLoader());
            chapters.put(key, chapter);
        }

        description = in.readString();
        genreID = in.readString();
    }

    // Tạo một Creator để khôi phục đối tượng từ Parcel
    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}