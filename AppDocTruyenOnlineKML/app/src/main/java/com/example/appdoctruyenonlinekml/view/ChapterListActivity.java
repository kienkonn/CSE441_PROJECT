package com.example.appdoctruyenonlinekml.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton; // Nhập lớp ImageButton
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyenonlinekml.R;
import com.example.appdoctruyenonlinekml.adapter.ChapterAdapter;
import com.example.appdoctruyenonlinekml.model.Chapter;
import com.example.appdoctruyenonlinekml.viewmodel.ChapterViewModel;

import java.util.ArrayList;

public class ChapterListActivity extends AppCompatActivity implements ChapterAdapter.OnChapterClickListener {

    private ChapterViewModel chapterViewModel;
    private RecyclerView recyclerView;
    private ChapterAdapter chapterAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list); // Layout cho danh sách chương

        recyclerView = findViewById(R.id.recyclerViewChapterList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chapterAdapter = new ChapterAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(chapterAdapter);

        chapterViewModel = new ViewModelProvider(this).get(ChapterViewModel.class);
        String bookId = getIntent().getStringExtra("BOOK_ID");

        // Giả định bạn đã có phương thức lấy danh sách chương
        chapterViewModel.getChapters(bookId).observe(this, chapters -> {
            chapterAdapter.updateChapterList(chapters);
        });

        // Thiết lập sự kiện cho nút btnClose
        ImageButton btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> finish()); // Kết thúc Activity và trở về trang trước
    }

    @Override
    public void onChapterClick(String chapterId) {
        Intent intent = new Intent(this, ChapterActivity.class);
        intent.putExtra("BOOK_ID", getIntent().getStringExtra("BOOK_ID")); // Truyền ID sách
        intent.putExtra("CHAPTER_ID", chapterId); // Truyền ID chương
        startActivity(intent);
    }
}
