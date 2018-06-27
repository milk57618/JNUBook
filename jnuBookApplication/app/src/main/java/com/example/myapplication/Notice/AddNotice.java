package com.example.myapplication.Notice;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.MyBook.AddBookActivity;
import com.example.myapplication.MyBook.BookMarketActivity;
import com.example.myapplication.Notice.NoticeActivity;
import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AddNotice extends AppCompatActivity {

    EditText title;
    EditText con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);

        title = findViewById(R.id.input_title);
        con = findViewById(R.id.input_context);

        Button ok = findViewById(R.id.a_OK);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                notice noti = new notice(-1,"",title.getText().toString(),con.getText().toString());
                AddNotiRequest addNotiRequest = new AddNotiRequest(noti);
                addNotiRequest.execute();

            }
        });


        Button cancle = findViewById(R.id.a_CANCLE);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    //url : http://localhost:8080/jnuBookServer/Reply.jsp
    private class AddNotiRequest extends AsyncTask<Void,Void,String> {


        private notice noti;
        private String sendMsg;
        private String receiveMsg;

        public AddNotiRequest(notice noti){
            this.noti = noti;
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {

                String str;

                URL url = new URL("http://168.131.152.150:8080/jnuBookServer/SummitNotice.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");

                Log.i("공지사항 통신 시작", "!!");

                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

                //sendMsg = "id="+strings[0]+"&pwd="+strings[1];
                sendMsg = "n_id="+ -1 +"&title="+ noti.getTitle() +"&content="+ noti.getContent();
                osw.write(sendMsg);
                osw.flush();

                Log.i("공지사항 통신 쓰기작업 시작", "에러");

                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("공지사항 통신 결과", "에러");
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
            Intent intent = new Intent(AddNotice.this, NoticeActivity.class);
            startActivity(intent);
        }
    }
}

/*
*
 if(request.getParameter("n_id") != null){

 if(request.getParameter("title") != null){

 if(request.getParameter("content") != null){

  */