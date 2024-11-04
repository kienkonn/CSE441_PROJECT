package com.example.appdoctruyenonlinekml;


import android.os.Parcel;
import android.os.Parcelable;

public class Chapter implements Parcelable {
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

    // Phương thức để viết đối tượng vào Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(chapterID);
        dest.writeInt(chapterNumber);
        dest.writeString(content);
        dest.writeString(title);
    }

    // Phương thức để lấy kích thước của đối tượng
    @Override
    public int describeContents() {
        return 0;
    }

    // Tạo đối tượng từ Parcel
    protected Chapter(Parcel in) {
        chapterID = in.readString();
        chapterNumber = in.readInt();
        content = in.readString();
        title = in.readString();
    }

    // Tạo một Creator để khôi phục đối tượng từ Parcel
    public static final Creator<Chapter> CREATOR = new Creator<Chapter>() {
        @Override
        public Chapter createFromParcel(Parcel in) {
            return new Chapter(in);
        }

        @Override
        public Chapter[] newArray(int size) {
            return new Chapter[size];
        }
    };
}
