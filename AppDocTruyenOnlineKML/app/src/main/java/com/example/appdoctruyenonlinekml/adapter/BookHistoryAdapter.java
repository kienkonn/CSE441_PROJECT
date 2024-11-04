package com.example.appdoctruyenonlinekml.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyenonlinekml.R;
import com.example.appdoctruyenonlinekml.model.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookHistoryAdapter extends RecyclerView.Adapter<BookHistoryAdapter.BookHistoryViewHolder> {

    private List<Book> books;
    private OnBookHistoryClickListener onBookHistoryClickListener;

    public BookHistoryAdapter(OnBookHistoryClickListener listener) {
        this.onBookHistoryClickListener = listener;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged(); // Cập nhật giao diện
    }

    @NonNull
    @Override
    public BookHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_history, parent, false);
        return new BookHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHistoryViewHolder holder, int position) {
        Book book = books.get(position);
        holder.title.setText(book.getTitle());
        // Giả sử book.getImageUrl() trả về URL hình ảnh bìa sách
        Picasso.get().load(book.getImageUrl()).into(holder.coverImage);

//        // Giả sử bạn có một phương thức trong Book để lấy số chương hiện tại
//        holder.chapterNumber.setText("Chương " + book.getCurrentChapter()); // Hiển thị số chương đang đọc

        // Thiết lập sự kiện click cho nút thêm vào thư viện
        holder.addToLibraryButton.setOnClickListener(v -> {
            if (onBookHistoryClickListener != null) {
                onBookHistoryClickListener.onAddToLibraryClick(book); // Gọi callback
            }
        });

        // Nếu cần thiết, có thể thêm logic cho nút xóa
        holder.deleteButton.setOnClickListener(v -> {
            if (onBookHistoryClickListener != null) {
                onBookHistoryClickListener.onDeleteClick(book); // Gọi callback cho nút xóa
            }
        });
    }

    @Override
    public int getItemCount() {
        return books != null ? books.size() : 0;
    }

    public static class BookHistoryViewHolder extends RecyclerView.ViewHolder {
        ImageView coverImage;
        TextView title;
        TextView chapterNumber; // Thêm TextView để hiển thị số chương
        ImageButton addToLibraryButton;
        ImageButton deleteButton;

        public BookHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            coverImage = itemView.findViewById(R.id.book_cover_image);
            title = itemView.findViewById(R.id.book_title);
            chapterNumber = itemView.findViewById(R.id.txt_chapter_number); // ID của TextView hiển thị số chương
            addToLibraryButton = itemView.findViewById(R.id.edit_button); // ID của nút thêm vào thư viện
            deleteButton = itemView.findViewById(R.id.delete_button); // ID của nút xóa
        }
    }

    // Interface để xử lý sự kiện click
    public interface OnBookHistoryClickListener {
        void onAddToLibraryClick(Book book); // Callback cho thêm vào thư viện
        void onDeleteClick(Book book); // Callback cho nút xóa
    }
}
