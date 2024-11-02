package com.example.appdoctruyenonlinekml.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appdoctruyenonlinekml.R;
import com.example.appdoctruyenonlinekml.adapter.BookAdapter;
import com.example.appdoctruyenonlinekml.model.Book;
import java.util.List;

public class HistoryFragment extends Fragment implements BookAdapter.OnBookClickListener {

    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reading_history, container, false);
        recyclerView = view.findViewById(R.id.rvReadingHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        bookAdapter = new BookAdapter(this);
        recyclerView.setAdapter(bookAdapter);

        loadReadingHistory(); // Tải lịch sử đọc từ nguồn dữ liệu
        return view;
    }

    private void loadReadingHistory() {
        // Thêm logic để tải lịch sử đọc từ Firebase hoặc nguồn dữ liệu khác
        List<Book> readingHistory = ...; // Lấy danh sách sách trong lịch sử đọc
        bookAdapter.setBooks(readingHistory);
    }

    @Override
    public void onBookClick(Book book) {
        // Xử lý sự kiện khi nhấn vào sách
    }
}
