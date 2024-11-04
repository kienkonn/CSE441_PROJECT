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
import com.example.appdoctruyenonlinekml.adapter.BookHistoryAdapter;
import com.example.appdoctruyenonlinekml.model.Book;
import com.example.appdoctruyenonlinekml.model.User;
import com.example.appdoctruyenonlinekml.repository.BookRepository;
import com.example.appdoctruyenonlinekml.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment implements BookHistoryAdapter.OnBookHistoryClickListener {
    private UserRepository userRepository;
    private BookRepository bookRepository;
    private RecyclerView recyclerViewHistory;
    private BookHistoryAdapter bookHistoryAdapter;
    private List<User.ReadingHistory> readingHistoryList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reading_history, container, false);

        // Khởi tạo RecyclerView
        recyclerViewHistory = view.findViewById(R.id.rvReadingHistory);
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        bookHistoryAdapter = new BookHistoryAdapter(this);
        recyclerViewHistory.setAdapter(bookHistoryAdapter);

        // Khởi tạo danh sách lịch sử đọc
        readingHistoryList = new ArrayList<>();

        // Khởi tạo UserRepository và BookRepository
        userRepository = new UserRepository();
        bookRepository = new BookRepository();
        String userId = "userID1"; // Thay bằng ID người dùng thực tế

        userRepository.getUserData(userId, new UserRepository.OnUserDataLoadedCallback() {
            @Override
            public void onUserDataLoaded(User user) {
                // Lấy lịch sử đọc và cập nhật vào adapter
                readingHistoryList.clear();
                readingHistoryList.addAll(user.getReadingHistory());
                getBooksFromReadingHistory(readingHistoryList);
            }

            @Override
            public void onError(Exception e) {
                // Xử lý lỗi khi không thể tải lịch sử đọc
            }
        });

        return view;
    }

    @Override
    public void onAddToLibraryClick(Book book) {
        // Logic để lưu sách vào thư viện
        saveBookToLibrary(book);
    }

    @Override
    public void onDeleteClick(Book book) {
        // Logic để xóa sách khỏi lịch sử nếu cần
        deleteBookFromHistory(book);
    }

    private void saveBookToLibrary(Book book) {
        // Gọi phương thức từ ViewModel để lưu vào thư viện
        // bookViewModel.addBookToLibrary(book);
    }

    private void deleteBookFromHistory(Book book) {
        // Logic để xóa sách khỏi lịch sử
    }

    private void getBooksFromReadingHistory(List<User.ReadingHistory> readingHistoryList) {
        List<Book> books = new ArrayList<>();
        for (User.ReadingHistory entry : readingHistoryList) {
            // Gọi phương thức để lấy Book từ bookId
            String bookId = entry.getBookID();
            bookRepository.getBookById(bookId, new BookRepository.OnBookLoadedCallback() {
                @Override
                public void onBookLoaded(Book book) {
                    // Xử lý khi sách được tải thành công
                    if (book != null) {
                        books.add(book);
                        bookHistoryAdapter.setBooks(books);
                    }
                }

                @Override
                public void onError(Exception e) {
                    // Xử lý lỗi khi không thể tải sách
                }
            });
        }
    }
}
