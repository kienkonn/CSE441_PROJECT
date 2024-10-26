package com.example.appdoctruyenonlinekml;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyenonlinekml.adapter.BookAdapter;
import com.example.appdoctruyenonlinekml.model.Book;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private List<Book> bookList;

//    // Firebase Database reference
//    private FirebaseDatabase database;
//    private DatabaseReference bookRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo RecyclerView
        recyclerView = findViewById(R.id.rvHotStories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Khởi tạo danh sách và adapter
        bookList = new ArrayList<>();
        bookAdapter = new BookAdapter(this, bookList);
        recyclerView.setAdapter(bookAdapter);

//        // Firebase Database reference
//        database = FirebaseDatabase.getInstance();
//        bookRef = database.getReference("books");
//
//        // Lắng nghe sự thay đổi dữ liệu từ Firebase và cập nhật bookList
//        bookRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                bookList.clear();  // Xóa dữ liệu cũ
//                for (DataSnapshot bookSnapshot : snapshot.getChildren()) {
//                    Book book = bookSnapshot.getValue(Book.class);
//                    bookList.add(book);  // Thêm dữ liệu mới vào danh sách
//                }
//                bookAdapter.notifyDataSetChanged();  // Cập nhật RecyclerView
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Xử lý lỗi nếu có
//            }
//        });
    }
}
