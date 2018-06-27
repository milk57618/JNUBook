package com.example.myapplication.Notice;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.User;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {

    private ListView noticeListView;
    private notice_adapter adapter;
    public static List<notice> noticeList;
    public static int order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        getnotice notice = new getnotice("http://168.131.152.150:8080/jnuBookServer/Notice.jsp");
        notice.execute();

        noticeListView = (ListView) findViewById(R.id.notice_list);

        noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                order = (int)l;
                Intent intent = new Intent(NoticeActivity.this, NoticeText.class);
                startActivity(intent);
            }
        });

        Button btn = findViewById(R.id.notice_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(User.getInstance().getAdmincheck()){
                    Intent intent = new Intent(NoticeActivity.this, AddNotice.class);
                    startActivity(intent);}
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(NoticeActivity.this);
                    builder.setMessage("관리자만 업로드 할 수 있습니다.")
                            .setNegativeButton("확인", null)
                            .create()
                            .show();
                }
            }
        });


    }

    private class getnotice extends AsyncTask<Void,Void,String> {

        private String url;
        private int posNum;
        private String date;
        noticeConvert ConvertValue;

        public getnotice(String url){
            this.url=url;
        }


        @Override
        protected String doInBackground(Void... voids) {

            String result;
            noticeRequest memberRequest =new noticeRequest();
            result = memberRequest.request(url);

            if (result!=null)
                result=result.trim();

            ConvertValue = new noticeConvert();
            ConvertValue.getData(result);
            return result;

        }

        @Override
        protected void onPostExecute(String s) {
            //문자 스트림을 json 데이터로 변환

            noticeList = new ArrayList<notice>();
            noticeList = ConvertValue.getNoticeList();
            adapter = new notice_adapter(getApplicationContext(),noticeList);
            noticeListView.setAdapter(adapter);
            Log.d("엑",s);
        }
    }
}
