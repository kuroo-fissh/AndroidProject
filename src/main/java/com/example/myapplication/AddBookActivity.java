package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.InfoClass.book;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddBookActivity extends AppCompatActivity {
    private Button isbn_search_button;
    private EditText isbn_input_bar;
    private Button isbn_add_button;
    private String isbn;
    private String address = "https://api.jike.xyz/situ/book/isbn/";
    private String apikey = "?apikey=12718.2336c4d830e490842883539cad5a9668.d596f160ca12154d2fa00b1aef5c9045";
    private TextView book_name;
    private TextView book_author;
    private TextView book_press;
    private TextView book_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        //isbn_add_button.setActivated(false);
        isbn_search_button = findViewById(R.id.isbn_search_button);
        isbn_add_button = findViewById(R.id.isbn_add_button);
        isbn_input_bar = findViewById(R.id.isbn_input_bar);
        book_name = findViewById(R.id.book_name);
        book_author = findViewById(R.id.book_author);
        book_press = findViewById(R.id.book_press);
        book_year = findViewById(R.id.book_year);
        isbn_search_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.v("AddBookActivity", "---------click button-------");
                isbn = isbn_input_bar.getText().toString();
                Log.v("AddBookActivity", isbn);
                String url = address + isbn + apikey;
                Log.v("AddBookActivity", url);
                RequestQueue requestQueue = Volley.newRequestQueue(AddBookActivity.this);
                //isbn="9787806767245";
                //9787506380263
                //9787536457577
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                        url, null, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.v("AddBookActivity", "json:" + response);
                            Log.v("json","eyyyy");
                            JSONObject jsonObject = response.getJSONObject("data");
                            Log.v("json","new jsonobject:" + jsonObject);
                            String name = jsonObject.getString("name");
                            String author = jsonObject.getString("author");
                            String press = jsonObject.getString("publishing");
                            String year = jsonObject.getString("published");
                            book_name.setText(name);
                            book_author.setText(author);
                            book_press.setText(press);
                            book_year.setText(year);
                            if(book_name != null )
                            {
                                Log.v("add_button", "ppooooggg");
                                isbn_add_button.setEnabled(true);
                                isbn_add_button.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View v) {
                                        book newbook = new book(name,author,press,year, " ");
                                        boolean dup_flag = false;
                                        InfoClass info = (InfoClass)getApplicationContext();
                                        Log.v("duplicate", "info size: " + InfoClass.bookinfo.size());
                                        for(int i = 0; i < InfoClass.bookinfo.size(); i++){
                                            if(newbook.equals(InfoClass.bookinfo.get(i))){
                                                dup_flag = true;
                                            }
                                        }
                                        if(dup_flag == false){
                                            info.addbook(newbook);
                                            new AlertDialog.Builder(AddBookActivity.this).setMessage("Successfully added???")
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {

                                                        }
                                                    }).show();
                                        }
                                        else {
                                            new AlertDialog.Builder(AddBookActivity.this).setMessage("The book is already on your shelf???")
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {

                                                        }
                                                    }).show();
                                        }
                                    }
                                });
                            }

                        } catch (JSONException e) {
                             Log.v("json", "ppooooggg");
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("AddBookActivity", "error");
                        System.out.println("????????????????????????");
                        error.printStackTrace();
                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}
