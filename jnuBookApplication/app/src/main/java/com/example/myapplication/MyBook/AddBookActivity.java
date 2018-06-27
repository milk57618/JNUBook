package com.example.myapplication.MyBook;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.Activity.HomeActivity;
import com.example.myapplication.Activity.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AddBookActivity extends AppCompatActivity {
    String[] statelist_item;
    ArrayAdapter major_adapter;
    ArrayAdapter<String> state_adapter;
    Spinner majorSpinner;
    Spinner stateSpinner;
    EditText title;
    EditText price;
    EditText contents;
    EditText lectname;
    EditText bookname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        title = findViewById(R.id.input_title);
        price = findViewById(R.id.input_price);
        contents = findViewById(R.id.input_context);
        lectname = findViewById(R.id.lectName);
        bookname = findViewById(R.id.bookName);

        majorSpinner =findViewById(R.id.a_major);
        major_adapter = ArrayAdapter.createFromResource(this, R.array.major, android.R.layout.simple_spinner_dropdown_item);
        majorSpinner.setAdapter(major_adapter);

        stateSpinner =findViewById(R.id.a_state);
        statelist_item = new String[]{"최상", "보통", "좋지않음"};
        state_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, statelist_item);
        state_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(state_adapter);




        //DB에 저장
        Button ok = findViewById(R.id.a_OK);
        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Book add_book = new Book(1, bookname.getText().toString(),lectname.getText().toString(),1,1, title.getText().toString(), contents.getText().toString(),3,
                        Integer.parseInt(User.getInstance().getId()), "" ,Integer.parseInt( price.getText().toString() ) );
                AddBookRequest addBookRequest= new AddBookRequest(add_book);
                addBookRequest.execute();

            }

        });
        Button cancle = findViewById(R.id.a_CANCLE);
        cancle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //url : http://localhost:8080/jnuBookServer/Reply.jsp
    private class AddBookRequest extends AsyncTask<Void,Void,String> {

        private String url;
        private Book book;
        private String sendMsg;
        private String receiveMsg;

        public AddBookRequest(Book book){
            this.book = book;
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {

                String str;

                URL url = new URL("http://168.131.152.150:8080/jnuBookServer/SummitBook.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");

                Log.i("책추가 통신 시작", "!!");

                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

                //sendMsg = "id="+strings[0]+"&pwd="+strings[1];
                sendMsg = "b_id="+ -1 +"&b_name="+ book.getB_name() +"&lect_name="+ book.getLect_name() +"&title="+ book.getTitle() +  "&content="+ book.getContent()
                            +  "&major_id="+ book.getMajor_id() +  "&grade="+ book.getGrade() + "&state="+ book.getState() + "&user_id="+ book.getUser_id() + "&price="+ book.getPrice();
                osw.write(sendMsg);
                osw.flush();

                Log.i("책추가 통신 쓰기작업 시작", "에러");

                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("책추가 통신 결과", "에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return  receiveMsg;

        }

        @Override
        protected void onPostExecute(String s) {
            //문자 스트림을 json 데이터로 변환
            Intent intent = new Intent(AddBookActivity.this, BookMarketActivity.class);
            startActivity(intent);
        }
    }
}



/*
 if(request.getParameter("b_name") != null){

         if(request.getParameter("lect_name") != null){

         if(request.getParameter("title") != null){

         if(request.getParameter("content") != null){


         if(request.getParameter("major_id") != null){

         if(request.getParameter("grade") != null){

         }
         if(request.getParameter("state") != null){

         }
         if(request.getParameter("user_id") != null){


         if(request.getParameter("price") != null){
         try{

         }*/
