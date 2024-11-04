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

    private List<Book> books;
    private OnBookClickListener onBookClickListener;

    public BookAdapter(OnBookClickListener listener) {
        this.onBookClickListener = listener;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged(); // Cập nhật giao diện
    }
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.title.setText(book.getTitle());
        Picasso.get().load(book.getImageUrl()).into(holder.image);

        // Thiết lập sự kiện click cho item
        holder.itemView.setOnClickListener(v -> {
            if (onBookClickListener != null) {
                onBookClickListener.onBookClick(book); // Gọi callback
            }
        });
    }
    @Override
    public int getItemCount() {
        return books != null ? books.size() : 0;
    }
    public static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.book_image);
            title = itemView.findViewById(R.id.book_title);
        }
    }
    // Interface để xử lý sự kiện click
    public interface OnBookClickListener {
        void onBookClick(Book book);
    }
}
