package com.example.myapplication;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.Comparator;

public class InfoClass extends Application{
    public static ArrayList<book> bookinfo = new ArrayList<book>();

    static class book{
        String name;
        String author;
        String press;
        String year;
        String review;
        float rating;
        boolean isEditing;

        public book(String name, String author, String press, String year, String review){
            this.name = name;
            this.author = author;
            this.press = press;
            this.year = year;
            this.review = review;
            this.rating = 0.0F;
            this.isEditing = false;
        }

        public String getName() {
            return name;
        }

        public String getAuthor() {
            return author;
        }

        public String getPress() {
            return press;
        }

        public String getYear() {
            return year;
        }

        public boolean equals(book b){
            if(name == b.name && author == b.author && press == b.press && year == b.year)
                return true;
            return false;
        }
    }
    public void addbook(book newbook){
        bookinfo.add(newbook);
    }
    public void showbook(){
        Log.v("show123","test");
        for (int i = 0 ;i<bookinfo.size();i++){
            Log.v("show123",bookinfo.get(i).name.toString());
        }
    }

    public void sortbyauthor(){
        showbook();
        for(int i = 0; i < bookinfo.size(); i++){
            for(int j = 0; j < bookinfo.size() - 1 - i; j++){
                Log.v("sort", "j : " + bookinfo.get(j).author + " j+1 : " + bookinfo.get(j+1).author + "res: " + bookinfo.get(j).author.compareTo(bookinfo.get(j+1).author));
                if(bookinfo.get(j).author.compareTo(bookinfo.get(j + 1).author) > 0){
                    Log.v("sort", bookinfo.get(j).author + " , " + bookinfo.get(j + 1).author);
                    book tmp = bookinfo.get(j);
                    bookinfo.set(j, bookinfo.get(j + 1));
                    bookinfo.set(j + 1, tmp);
                    Log.v("sort", bookinfo.get(j).author + " , " + bookinfo.get(j + 1).author);
                }
            }
        }
        showbook();
    }

    public void sortbyyear(){
        showbook();
        for(int i = 0; i < bookinfo.size(); i++){
            for(int j = 0; j < bookinfo.size() - 1 - i; j++){
                Log.v("sort", "j : " + bookinfo.get(j).year + " j+1 : " + bookinfo.get(j+1).year + "res: " + bookinfo.get(j).year.compareTo(bookinfo.get(j+1).year));
                if(bookinfo.get(j).year.compareTo(bookinfo.get(j + 1).year) > 0){
                    Log.v("sort", bookinfo.get(j).year + " , " + bookinfo.get(j + 1).year);
                    book tmp = bookinfo.get(j);
                    bookinfo.set(j, bookinfo.get(j + 1));
                    bookinfo.set(j + 1, tmp);
                    Log.v("sort", bookinfo.get(j).year + " , " + bookinfo.get(j + 1).year);
                }
            }
        }
        showbook();
    }
}
