package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class BookReviewActivity extends AppCompatActivity {
    private EditText book_review_body;
    private Button book_review_button;
    private RatingBar book_rating_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_review);
        book_review_body = findViewById(R.id.book_review_body);
        book_review_button = findViewById(R.id.book_review_button);
        book_rating_bar = findViewById(R.id.book_rating_bar);

        for(int i = 0; i < InfoClass.bookinfo.size(); i++) {
            if(InfoClass.bookinfo.get(i).isEditing == true)
            {
                Log.v("position", "Set name：" + InfoClass.bookinfo.get(i).name + " rating : " + InfoClass.bookinfo.get(i).rating);
                book_review_body.setText(InfoClass.bookinfo.get(i).review);
                book_rating_bar.setRating(InfoClass.bookinfo.get(i).rating);
            }
        }

        book_review_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < InfoClass.bookinfo.size(); i++) {
                    if(InfoClass.bookinfo.get(i).isEditing == true){
                        Log.v("position", "name：" + InfoClass.bookinfo.get(i).name + "isEditing: " + InfoClass.bookinfo.get(i).isEditing);
                        InfoClass.bookinfo.get(i).review = book_review_body.getText().toString();
                        InfoClass.bookinfo.get(i).isEditing = false;
                        Intent intent = new Intent(BookReviewActivity.this, BookListActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
        for(int i = 0; i < InfoClass.bookinfo.size(); i++) {
            if(InfoClass.bookinfo.get(i).isEditing == true){
                Log.v("book review", "book review: " + InfoClass.bookinfo.get(i).review);
            }
        }

        book_rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float point = ((rating / ratingBar.getNumStars()) * 5);
                Log.v("rating", "rating : " + point);
                for(int i = 0; i < InfoClass.bookinfo.size(); i++) {
                    if(InfoClass.bookinfo.get(i).isEditing == true){
                        InfoClass.bookinfo.get(i).rating = point;
                        Log.v("rating", "name: "+ InfoClass.bookinfo.get(i).name + " rating : " + point);
                    }
                }
            }
        });
    }
}