package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BookListActivity extends AppCompatActivity {

    private Button sort_button;
    private TextView manage_menu;

    public static class bookAdatper extends  RecyclerView.Adapter<bookAdatper.ViewHolder>{
        private List<InfoClass.book> myBookList;
        private bookAdatper.OnItemClickListener onItemClickListener;

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
             holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                 @Override
                 public boolean onLongClick(View v) {
                     int position = holder.getLayoutPosition();
                     onItemClickListener.onItemLongClick(holder.itemView, position);
                     return true;
                 }
             });
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

        public interface OnItemClickListener{
            void onItemLongClick(View view,int position);
        }

        public void setOnItemClickListener(bookAdatper.OnItemClickListener listener){
            this.onItemClickListener = listener;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        //拿一下那个RecyclerView
        //9787806767245
        RecyclerView recyclerView  = (RecyclerView) findViewById(R.id.recyclerView);
        //InfoClass.bookinfo.add(new InfoClass.book("name","author","press","year"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final bookAdatper adatper = new bookAdatper(InfoClass.bookinfo);
        Log.v("adapter", "adapter listener works");
        adatper.setOnItemClickListener(new bookAdatper.OnItemClickListener(){
            @Override
            public void onItemLongClick(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookListActivity.this);
                builder.setMessage("Delete item?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        InfoClass.bookinfo.remove(position);
                        adatper.notifyDataSetChanged();
                    }
                });
                builder.setNeutralButton("cancel", null);
                builder.create().show();
            }
        });

        recyclerView.setAdapter(adatper);

        sort_button = findViewById(R.id.sort_button);
        sort_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sort_button.getText().toString() == "Sort by author") {
                    sort_button.setText("Sort by year");
                    Log.v("sort", "sort by author");
                    InfoClass info = (InfoClass)getApplicationContext();
                    info.sortbyauthor();
                    recyclerView.setAdapter(adatper);
                }
                else{
                    sort_button.setText("Sort by author");
                    InfoClass info = (InfoClass)getApplicationContext();
                    info.sortbyyear();
                    recyclerView.setAdapter(adatper);
                }
            }
        });
    }
}