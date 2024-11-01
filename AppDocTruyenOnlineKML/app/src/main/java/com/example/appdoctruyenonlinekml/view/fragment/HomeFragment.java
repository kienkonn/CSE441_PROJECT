package com.example.appdoctruyenonlinekml.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appdoctruyenonlinekml.R;
import com.example.appdoctruyenonlinekml.adapter.BookAdapter;
import com.example.appdoctruyenonlinekml.view.BookInfoActivity;
import com.example.appdoctruyenonlinekml.viewmodel.BooksViewModel;

public class HomeFragment extends Fragment {
    private BooksViewModel booksViewModel;
    private BookAdapter bookAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout cho fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rvReading);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        // Khởi tạo adapter với listener
        bookAdapter = new BookAdapter(book -> {
            Intent intent = new Intent(getActivity(), BookInfoActivity.class);
            intent.putExtra("BOOK_EXTRA", book); // Truyền dữ liệu book qua intent
            startActivity(intent);
        });

        recyclerView.setAdapter(bookAdapter);
        // Khởi tạo ViewModel
        booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        // Quan sát dữ liệu sách từ ViewModel
        booksViewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
            if (books != null) {
                bookAdapter.setBooks(books);
            }
        });

        return view;
    }
}
