package com.example.appdoctruyenonlinekml.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class User implements Parcelable {
    private String userId;
    private String username;
    private String email;
    private List<String> favoriteBooks;
    private List<ReadingHistory> readingHistory;

    public User(String userId, String username, String email, List<String> favoriteBooks, List<ReadingHistory> readingHistory) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.favoriteBooks = favoriteBooks;
        this.readingHistory = readingHistory;
    }

    // Getter và Setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getFavoriteBooks() {
        return favoriteBooks;
    }

    public void setFavoriteBooks(List<String> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
    }

    public List<ReadingHistory> getReadingHistory() {
        return readingHistory;
    }

    public void setReadingHistory(List<ReadingHistory> readingHistory) {
        this.readingHistory = readingHistory;
    }

    // Phương thức để viết đối tượng vào Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeStringList(favoriteBooks);
        dest.writeTypedList(readingHistory);
    }

    // Phương thức để lấy kích thước của đối tượng
    @Override
    public int describeContents() {
        return 0;
    }

    // Tạo đối tượng từ Parcel
    protected User(Parcel in) {
        userId = in.readString();
        username = in.readString();
        email = in.readString();
        favoriteBooks = in.createStringArrayList();
        readingHistory = in.createTypedArrayList(ReadingHistory.CREATOR);
    }

    // Tạo một Creator để khôi phục đối tượng từ Parcel
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    // Lớp để đại diện cho lịch sử đọc
    public static class ReadingHistory implements Parcelable {
        private String bookID;
        private int lastReadChapter;
        private int lastReadPosition;

        public ReadingHistory(String bookID, int lastReadChapter, int lastReadPosition) {
            this.bookID = bookID;
            this.lastReadChapter = lastReadChapter;
            this.lastReadPosition = lastReadPosition;
        }

        // Getter và Setter
        public String getBookID() {
            return bookID;
        }

        public void setBookID(String bookID) {
            this.bookID = bookID;
        }

        public int getLastReadChapter() {
            return lastReadChapter;
        }

        public void setLastReadChapter(int lastReadChapter) {
            this.lastReadChapter = lastReadChapter;
        }

        public int getLastReadPosition() {
            return lastReadPosition;
        }

        public void setLastReadPosition(int lastReadPosition) {
            this.lastReadPosition = lastReadPosition;
        }

        // Phương thức để viết đối tượng vào Parcel
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(bookID);
            dest.writeInt(lastReadChapter);
            dest.writeInt(lastReadPosition);
        }

        // Phương thức để lấy kích thước của đối tượng
        @Override
        public int describeContents() {
            return 0;
        }

        // Tạo đối tượng từ Parcel
        protected ReadingHistory(Parcel in) {
            bookID = in.readString();
            lastReadChapter = in.readInt();
            lastReadPosition = in.readInt();
        }

        // Tạo một Creator để khôi phục đối tượng từ Parcel
        public static final Creator<ReadingHistory> CREATOR = new Creator<ReadingHistory>() {
            @Override
            public ReadingHistory createFromParcel(Parcel in) {
                return new ReadingHistory(in);
            }

            @Override
            public ReadingHistory[] newArray(int size) {
                return new ReadingHistory[size];
            }
        };
    }
}
