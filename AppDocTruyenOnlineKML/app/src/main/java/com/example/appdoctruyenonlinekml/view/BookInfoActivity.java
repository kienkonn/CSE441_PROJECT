package com.example.appdoctruyenonlinekml.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appdoctruyenonlinekml.R;
import com.example.appdoctruyenonlinekml.model.Book;
import com.example.appdoctruyenonlinekml.repository.BookRepository;
import com.squareup.picasso.Picasso;

public class BookInfoActivity extends AppCompatActivity {
    private TextView tvTitle, tvAuthor, tvStatus, tvRating, tvViews, tvChapters;
    private ImageView ivBookCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Kích hoạt nút trở lại
        }

        // Khởi tạo các view
        tvTitle = findViewById(R.id.tvBookTitle);
        tvAuthor = findViewById(R.id.tvBookAuthor);
        tvStatus = findViewById(R.id.tvBookStatus);
        tvRating = findViewById(R.id.tvBookRating);
        tvViews = findViewById(R.id.tvBookViews);
        tvChapters = findViewById(R.id.tvBookChapter);
        ivBookCover = findViewById(R.id.ivBookCover);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("BOOK_EXTRA");

        // Cập nhật UI với dữ liệu sách
        if (book != null) {
            tvTitle.setText(book.getTitle());
            tvStatus.setText(book.getStatus());
            tvRating.setText("Đánh giá: " + book.getRating());
            tvViews.setText("Lượt xem: " + book.getViews());
            tvChapters.setText("Số chương: " + (book.getChapters() != null ? book.getChapters().size() : 0));
            Picasso.get().load(book.getImageUrl()).into(ivBookCover);

            // Lấy tên tác giả
            BookRepository repository = new BookRepository();
            repository.getAuthorName(book.getAuthorId(), new BookRepository.OnAuthorLoadedCallback() {
                @Override
                public void onAuthorLoaded(String authorName) {
                    tvAuthor.setText(authorName != null ? authorName : "Chưa có thông tin");
                }
            });
        }


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Quay lại màn hình trước
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}