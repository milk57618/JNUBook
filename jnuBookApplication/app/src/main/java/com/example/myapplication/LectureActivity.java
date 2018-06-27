package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.MyBook.AddBookActivity;
import com.example.myapplication.MyBook.BookMarketActivity;
import com.example.myapplication.Notice.AddNotice;
import com.example.myapplication.Notice.NoticeActivity;
import com.example.myapplication.Notice.NoticeText;
import com.example.myapplication.Notice.notice;
import com.example.myapplication.Notice.noticeConvert;
import com.example.myapplication.Notice.noticeRequest;
import com.example.myapplication.Notice.notice_adapter;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LectureActivity extends AppCompatActivity {

    private ListView lectureListView;
    private lecture_adapter adapter;
    public static List<lecture> lectureList;
    public static int order;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);

        getlecture lecture = new getlecture("http://168.131.152.150:8080/jnuBookServer/Lecture.jsp");
        lecture.execute();

        lectureListView = (ListView) findViewById(R.id.lecture_list);

        lectureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                order = (int)l;
                Intent intent = new Intent(LectureActivity.this, LectureDetailActivity.class);
                startActivity(intent);
            }
        });

        fab = findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LectureActivity.this, AddLectureActivity.class);
                startActivity(intent);
            }
        });

    }


    private class getlecture extends AsyncTask<Void,Void,String> {

        private String url;
        private int posNum;
        private String date;
        lectureConvert ConvertValue;

        public getlecture(String url){
            this.url=url;
        }

        @Override
        protected String doInBackground(Void... voids) {

            String result;
            lectureRequest Req = new lectureRequest();
            result = Req.request(url);

            if (result!=null)
                result=result.trim();

            ConvertValue = new lectureConvert();
            ConvertValue.getData(result);
            Log.d("강의정보 결과",result);
            return result;

        }

        @Override
        protected void onPostExecute(String s) {
            //문자 스트림을 json 데이터로 변환

            lectureList = new ArrayList<lecture>();
            lectureList = ConvertValue.getLectureList();
            adapter = new lecture_adapter(getApplicationContext(), lectureList);
            lectureListView.setAdapter(adapter);
            Log.d("엑213123",s);
        }
    }
}
