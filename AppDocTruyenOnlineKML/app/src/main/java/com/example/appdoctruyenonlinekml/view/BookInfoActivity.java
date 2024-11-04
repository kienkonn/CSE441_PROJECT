package com.example.appdoctruyenonlinekml.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.appdoctruyenonlinekml.R;
import com.example.appdoctruyenonlinekml.model.Book;
import com.example.appdoctruyenonlinekml.repository.BookRepository;
import com.example.appdoctruyenonlinekml.viewmodel.BooksViewModel;
import com.example.appdoctruyenonlinekml.viewmodel.ChapterViewModel;
import com.example.appdoctruyenonlinekml.viewmodel.UserViewModel;
import com.squareup.picasso.Picasso;

public class BookInfoActivity extends AppCompatActivity {
    // Khai báo các view
    private TextView tvTitle, tvAuthor, tvStatus, tvRating, tvViews, tvChapters;
    private ImageView ivBookCover;
    private ImageButton btnChapterList, btnRead;
    private ImageButton btnAddToLibrary; // Khai báo btnAddToLibrary

    private ChapterViewModel chapterViewModel;
    private BooksViewModel bookViewModel; // Khai báo BooksViewModel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        // Thiết lập Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Kích hoạt nút trở lại
        }

        // Khởi tạo các view
        initializeViews();

        // Khởi tạo ViewModels
        chapterViewModel = new ViewModelProvider(this).get(ChapterViewModel.class);
        bookViewModel = new ViewModelProvider(this).get(BooksViewModel.class); // Khởi tạo BooksViewModel

        // Nhận dữ liệu từ Intent
        Book book = getIntent().getParcelableExtra("BOOK_EXTRA");

        // Cập nhật UI với dữ liệu sách
        if (book != null) {
            updateUIWithBookData(book);
        } else {
            tvTitle.setText("Thông tin sách không hợp lệ");
        }
    }

    private void initializeViews() {
        tvTitle = findViewById(R.id.tvBookTitle);
        tvAuthor = findViewById(R.id.tvBookAuthor);
        tvStatus = findViewById(R.id.tvBookStatus);
        tvRating = findViewById(R.id.tvBookRating);
        tvViews = findViewById(R.id.tvBookViews);
        tvChapters = findViewById(R.id.tvBookChapter);
        ivBookCover = findViewById(R.id.ivBookCover);
        btnChapterList = findViewById(R.id.btnChapterList);
        btnRead = findViewById(R.id.btnRead);
        btnAddToLibrary = findViewById(R.id.btnAddToLibrary); // Khởi tạo btnAddToLibrary
    }

    private String getUserId() {
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        return userViewModel.getCurrentUserId(); // Giả sử bạn có phương thức này trong UserViewModel
    }

    private void updateUIWithBookData(Book book) {
        tvTitle.setText(book.getTitle());
        tvStatus.setText(book.getStatus());
        tvRating.setText("Đánh giá: " + book.getRating());
        tvViews.setText("Lượt xem: " + book.getViews());
        tvChapters.setText("Số chương: " + (book.getChapters() != null ? book.getChapters().size() : 0));
        Picasso.get().load(book.getImageUrl()).into(ivBookCover);

        // Lấy tên tác giả
        loadAuthorName(book.getAuthorId());

        // Sự kiện khi nhấn vào btnChapterList
        btnChapterList.setOnClickListener(view -> {
            Intent chapterListIntent = new Intent(BookInfoActivity.this, ChapterListActivity.class);
            chapterListIntent.putExtra("BOOK_ID", book.getBookId());
            chapterListIntent.putExtra("TOTAL_CHAPTERS", book.getChapters().size()); // Thêm dòng này
            startActivity(chapterListIntent);
        });

        // Sự kiện khi nhấn vào btnRead
        btnRead.setOnClickListener(view -> {
            // Gọi chương đầu tiên bằng cách sử dụng chapterNumber
            if (book.getChapters() != null && !book.getChapters().isEmpty()) {
                int firstChapterNumber = 1; // Số thứ tự chương đầu tiên
                loadChapterByNumber(book.getBookId(), firstChapterNumber);
            } else {
                Toast.makeText(this, "Không có chương nào để đọc", Toast.LENGTH_SHORT).show();
            }
        });

        // Sự kiện khi nhấn vào btnAddToLibrary
        btnAddToLibrary.setOnClickListener(view -> {
            String userId = getUserId(); // Lấy ID người dùng thực tế
            if (userId != null) {
                bookViewModel.addBookToLibrary(book, userId); // Gọi phương thức thêm sách vào thư viện
                Toast.makeText(this, "Đã thêm vào tủ sách!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Không thể lấy ID người dùng", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadChapterByNumber(String bookId, int chapterNumber) {
        chapterViewModel.getChapterByNumber(bookId, chapterNumber).observe(this, chapter -> {
            if (chapter != null) {
                Intent readChapterIntent = new Intent(BookInfoActivity.this, ChapterActivity.class);
                readChapterIntent.putExtra("BOOK_ID", bookId);
                readChapterIntent.putExtra("CHAPTER_ID", chapter.getChapterID()); // Truyền ID chương
                startActivity(readChapterIntent);
            } else {
                Toast.makeText(this, "Chương không tồn tại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadAuthorName(String authorId) {
        if (authorId != null) {
            BookRepository repository = new BookRepository();
            repository.getAuthorName(authorId, new BookRepository.OnAuthorLoadedCallback() {
                @Override
                public void onAuthorLoaded(String authorName) {
                    tvAuthor.setText(authorName);
                }

                @Override
                public void onError(Exception e) {
                    Log.e("BookInfoActivity", "Error loading author: " + e.getMessage());
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
