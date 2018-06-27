package com.example.myapplication.MyBook;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.R;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

public class BookMarketActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private ListView booklist;
    private book_adapter adapter;
    public static int order;
    public static ArrayList<Book> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_market);

        booklist = (ListView) findViewById(R.id.bookmarket_list);


        booklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                order = (int)l;
                Intent intent = new Intent(BookMarketActivity.this, BookDetailActivity.class);
                startActivity(intent);
            }
        });


        getBook book = new getBook("http://168.131.152.150:8080/jnuBookServer/Book.jsp");
        book.execute();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookMarketActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });
    }


    private class getBook extends AsyncTask<Void,Void,String> {

        private String url;
        BookConvert bookValue;

        public getBook(String url){
            this.url=url;
        }


        @Override
        protected String doInBackground(Void... voids) {

            String result;
            Log.d("통신을 시작합니다....","!!!");
            BookRequest bookRequest =new BookRequest();
            result = bookRequest.request(url);

            if (result!=null)
                result=result.trim();

            Log.d("엑",result);
            bookValue = new BookConvert();
            bookValue.getData(result);
            Log.d("책 변환을 완료했습니다.","완료");

            return result;

        }

        @Override
        protected void onPostExecute(String s) {
            //문자 스트림을 json 데이터로 변환

            mList = new ArrayList<Book>();
            mList = bookValue.getBooksList();
            adapter = new book_adapter(getApplicationContext(),mList);

            booklist.setAdapter(adapter);
            Log.d("어댑터 등록 완료",s);

        }
    }

}
