package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Notice.AddNotice;
import com.example.myapplication.Notice.NoticeActivity;
import com.example.myapplication.Notice.notice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AddLectureActivity extends AppCompatActivity {

    EditText lect_name;
    EditText professor;
    EditText lect_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lecture);

        lect_name=findViewById(R.id.lecture_title);
        professor=findViewById(R.id.pro);
        lect_content=findViewById(R.id.lecture_add_context);

        Button ok = findViewById(R.id.lecture_OK);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lecture lect = new lecture(-1, lect_name.getText().toString(),"", professor.getText().toString(), lect_content.getText().toString(), Integer.parseInt(User.getInstance().getId())) ;
                AddLectRequest addLectRequest = new AddLectRequest(lect);
                addLectRequest.execute();

            }
        });

        Button cancle = findViewById(R.id.lecture_CANCLE);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //url : http://localhost:8080/jnuBookServer/SummitLecture.jsp
    private class AddLectRequest extends AsyncTask<Void,Void,String> {

        private lecture lect;
        private String sendMsg;
        private String receiveMsg;

        public AddLectRequest(lecture lect){
            this.lect = lect;
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {

                String str;

                URL url = new URL("http://168.131.152.150:8080/jnuBookServer/SummitLecture.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");

                Log.i("게시물 등록 통신 시작", "!!");

                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

                //sendMsg = "id="+strings[0]+"&pwd="+strings[1];
                sendMsg = "lect_id="+ -1 +"&lect_name="+ lect.getLecture_name() +"&prof_name="+ lect.getProfessor()+"&content="+lect.getLecture_content() +"&title="+"제목"
                        +"&user_id="+lect.getUser_id() ;
                osw.write(sendMsg);
                osw.flush();

                Log.i("게시물 등록 통신 쓰기작업 시작", "에러");

                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("게시물 등록 통신 결과", "에러");
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
            Log.d("게시물222 등록",s);
            //문자 스트림을 json 데이터로 변환
            Intent intent = new Intent(AddLectureActivity.this, LectureActivity.class);
            startActivity(intent);
        }
    }

}