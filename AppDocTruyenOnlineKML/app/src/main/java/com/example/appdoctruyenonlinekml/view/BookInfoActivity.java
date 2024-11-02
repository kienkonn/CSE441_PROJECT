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
import com.example.appdoctruyenonlinekml.model.Chapter;
import com.example.appdoctruyenonlinekml.repository.BookRepository;
import com.example.appdoctruyenonlinekml.viewmodel.ChapterViewModel;
import com.squareup.picasso.Picasso;

public class BookInfoActivity extends AppCompatActivity {
    // Khai báo các view
    private TextView tvTitle, tvAuthor, tvStatus, tvRating, tvViews, tvChapters;
    private ImageView ivBookCover;
    private ImageButton btnChapterList;
    private ImageButton btnRead;

    private ChapterViewModel chapterViewModel;

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

        // Khởi tạo ViewModel
        chapterViewModel = new ViewModelProvider(this).get(ChapterViewModel.class);

        // Nhận dữ liệu từ Intent
        Book book = (Book) getIntent().getSerializableExtra("BOOK_EXTRA");

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
                    tvAuthor.setText(authorName != null ? authorName : "Chưa có thông tin");
                }

                @Override
                public void onError(Exception e) {
                    Log.e("BookInfoActivity", "Error loading author name: " + e.getMessage());
                    tvAuthor.setText("Lỗi khi lấy tên tác giả");
                }
            });
        } else {
            tvAuthor.setText("Chưa có thông tin");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Quay lại màn hình trước
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
