package com.example.appdoctruyenonlinekml.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyenonlinekml.R;
import com.example.appdoctruyenonlinekml.model.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context context;
    private List<Book> bookList;  // Danh sách các đối tượng Book

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate item_book.xml để tạo View cho mỗi phần tử trong danh sách
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        // Lấy đối tượng Book ở vị trí tương ứng
        Book book = bookList.get(position);

        // Gán dữ liệu từ đối tượng Book vào ViewHolder
        holder.bookTitle.setText(book.getTitle());

        // Giả sử book có thuộc tính getImageUrl() để lấy URL của ảnh bìa
        Picasso.get().load(book.getImageUrl()).into(holder.bookImage);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    // Lớp ViewHolder quản lý các View trong item_book.xml
    public static class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView bookImage;
        TextView bookTitle;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            // Ánh xạ các View từ item_book.xml
            bookImage = itemView.findViewById(R.id.book_image);
            bookTitle = itemView.findViewById(R.id.book_title);
        }
    }
}
