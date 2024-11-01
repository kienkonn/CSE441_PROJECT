//package com.example.appdoctruyenonlinekml.view;
//
//import android.os.Bundle;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.appdoctruyenonlinekml.R;
//import com.example.appdoctruyenonlinekml.model.Book;
//import com.squareup.picasso.Picasso;
//
//public class BookDetailActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_book_info);
//
//        // Nhận dữ liệu từ intent
//        Book book = (Book) getIntent().getSerializableExtra("BOOK_EXTRA");
//
//        // Ánh xạ các view
//        TextView title = findViewById(R.id.tvBookTitle);
//        ImageView coverImage = findViewById(R.id.ivBookCover);
//
//        // Hiển thị thông tin sách
//        if (book != null) {
//            title.setText(book.getTitle());
//            Picasso.get().load(book.getImageUrl()).into(coverImage);
//        }
//    }
//}
