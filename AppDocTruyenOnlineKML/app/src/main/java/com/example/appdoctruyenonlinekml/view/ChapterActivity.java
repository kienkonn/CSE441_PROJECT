package com.example.appdoctruyenonlinekml.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.appdoctruyenonlinekml.R;
import com.example.appdoctruyenonlinekml.model.Chapter;
import com.example.appdoctruyenonlinekml.viewmodel.ChapterViewModel;

public class ChapterActivity extends AppCompatActivity {
    private ChapterViewModel chapterViewModel;
    private TextView chapterTitleTextView;
    private TextView chapterContentTextView;

    private String bookId;
    private int currentChapterNumber; // Biến lưu số thứ tự chương hiện tại

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_reading); // Đảm bảo bạn đã tạo layout này

        chapterTitleTextView = findViewById(R.id.tvChapterTitle);
        chapterContentTextView = findViewById(R.id.tvContent);

        chapterViewModel = new ViewModelProvider(this).get(ChapterViewModel.class);

        // Lấy dữ liệu từ Intent (ví dụ: từ ChapterListActivity)
        bookId = getIntent().getStringExtra("BOOK_ID");
        String chapterId = getIntent().getStringExtra("CHAPTER_ID");
        currentChapterNumber = getIntent().getIntExtra("CHAPTER_NUMBER", 1); // Lấy số thứ tự chương từ Intent, mặc định là 1

        if (bookId == null || chapterId == null) {
            Toast.makeText(this, "ID sách hoặc ID chương không hợp lệ", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadChapterById(chapterId); // Tải chương

        // Thiết lập sự kiện cho nút btnBack
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish()); // Kết thúc Activity và trở về trang trước

        // Thiết lập sự kiện cho các nút điều hướng
        ImageButton btnPrevious = findViewById(R.id.btn_previous);
        ImageButton btnNext = findViewById(R.id.btn_next);
        ImageButton btnList = findViewById(R.id.btn_list);

        btnPrevious.setOnClickListener(v -> {
            if (currentChapterNumber > 1) {
                // Chuyển đến chương trước
                loadChapterByNumber(currentChapterNumber - 1);
            } else {
                Toast.makeText(this, "Đây là chương đầu tiên", Toast.LENGTH_SHORT).show();
            }
        });

        btnNext.setOnClickListener(v -> {
            // Logic để chuyển đến chương tiếp theo
            loadChapterByNumber(currentChapterNumber + 1);
        });

        btnList.setOnClickListener(v -> openChapterList()); // Mở danh sách chương
    }

    private void loadChapterById(String chapterId) {
        chapterViewModel.getChapter(bookId, chapterId).observe(this, chapter -> {
            if (chapter != null) {
                updateChapterUI(chapter);
            } else {
                Toast.makeText(ChapterActivity.this, "Chương không tồn tại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateChapterUI(Chapter chapter) {
        chapterTitleTextView.setText(chapter.getTitle());
        chapterContentTextView.setText(chapter.getContent());
        currentChapterNumber = chapter.getChapterNumber();
    }

    private void loadChapterByNumber(int chapterNumber) {
        chapterViewModel.getChapterByNumber(bookId, chapterNumber).observe(this, chapter -> {
            if (chapter != null) {
                updateChapterUI(chapter);
            } else {
                Toast.makeText(ChapterActivity.this, "Chương không tồn tại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openChapterList() {
        // Tạo Intent để mở ChapterListActivity
        Intent chapterListIntent = new Intent(ChapterActivity.this, ChapterListActivity.class);
        chapterListIntent.putExtra("BOOK_ID", bookId); // Truyền ID sách qua Intent
        startActivity(chapterListIntent); // Mở ChapterListActivity
    }
}
