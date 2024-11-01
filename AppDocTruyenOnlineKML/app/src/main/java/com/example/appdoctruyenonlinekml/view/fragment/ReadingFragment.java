package com.example.appdoctruyenonlinekml.view.fragment;

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
import com.example.appdoctruyenonlinekml.viewmodel.BooksViewModel;

public class ReadingFragment extends Fragment {
    private BooksViewModel booksViewModel;
    private BookAdapter bookAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout cho fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rvReading);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        bookAdapter = new BookAdapter();
        recyclerView.setAdapter(bookAdapter);

        booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        booksViewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
            if (books != null) {
                bookAdapter.setBooks(books);
            }
        });

        return view;
    }
}
