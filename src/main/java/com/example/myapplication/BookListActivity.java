package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BookListActivity extends AppCompatActivity {

    private Button sort_button;

    public class bookAdatper extends  RecyclerView.Adapter<bookAdatper.ViewHolder>{
         private List<InfoClass.book> myBookList;

         @NonNull
         @Override
         public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
             View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
             ViewHolder viewHolder = new ViewHolder(view);
             return viewHolder;
         }

         @Override
         public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
             InfoClass.book book = myBookList.get(position);
             holder.bookname.setText(book.getName());
             holder.bookAuthor.setText(book.getAuthor());
             holder.bookyear.setText(book.getYear());
             holder.bookpress.setText(book.getPress());
         }

         @Override
         public int getItemCount() {
             return myBookList.size();
         }

         class ViewHolder extends RecyclerView.ViewHolder{
            TextView bookname;
            TextView bookAuthor;
            TextView bookpress;
            TextView bookyear;
            public ViewHolder(@NonNull View view) {
                super(view);
                bookname = (TextView)view.findViewById(R.id.show_name);
                bookAuthor = (TextView)view.findViewById(R.id.show_author);
                bookpress = (TextView)view.findViewById(R.id.show_press);
                bookyear = (TextView)view.findViewById(R.id.show_year);
            }
        }

        public bookAdatper(List<InfoClass.book> myBookList){
            this.myBookList = myBookList;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InfoClass info = (InfoClass)getApplicationContext();
        setContentView(R.layout.activity_book_list);
        //拿一下那个RecyclerView
        //9787806767245
        RecyclerView recyclerView  = (RecyclerView) findViewById(R.id.recyclerView);
        //InfoClass.bookinfo.add(new InfoClass.book("name","author","press","year"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new bookAdatper(InfoClass.bookinfo));

        sort_button = findViewById(R.id.sort_button);
        sort_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sort_button.getText().toString() == "Sort by author") {
                    sort_button.setText("Sort by year");
                    Log.v("sort", "sort by author");
                    InfoClass info = (InfoClass)getApplicationContext();
                    info.sortbyauthor();
                    recyclerView.setAdapter(new bookAdatper(InfoClass.bookinfo));
                }
                else{
                    sort_button.setText("Sort by author");
                    InfoClass info = (InfoClass)getApplicationContext();
                    info.sortbyyear();
                    recyclerView.setAdapter(new bookAdatper(InfoClass.bookinfo));
                }
            }
        });
        //如果类似userdefault可以用 但是没有要求
    }
}