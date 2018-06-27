package com.example.myapplication.Notice;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NoticeText extends AppCompatActivity {

    TextView title;
    TextView con;
    Button del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_text);

        title = findViewById(R.id.noti_title);
        con = findViewById(R.id.noti_context);
        del = findViewById(R.id.notice_del);

        title.setText(NoticeActivity.noticeList.get(NoticeActivity.order).getTitle());
        con.setText(NoticeActivity.noticeList.get(NoticeActivity.order).getContent());

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(User.getInstance().getAdmincheck()){
                    DelNoti delNoti= new DelNoti(NoticeActivity.noticeList.get(NoticeActivity.order).getN_id());
                    delNoti.execute();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(NoticeText.this);
                    builder.setMessage("관리자만 삭제 할 수 있습니다.")
                            .setNegativeButton("확인", null)
                            .create()
                            .show();
                }
            }
        });
    }


    private class DelNoti extends AsyncTask<Void,Void,String> {


        private int noti_id;
        private String sendMsg;
        private String receiveMsg;

        public DelNoti(int noti_id){
            this.noti_id = noti_id;
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {

                String str;

                URL url = new URL("http://168.131.152.150:8080/jnuBookServer/DeleteNotice.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                Log.i("공지사항삭제 통신 시작", "!!");

                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");

                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());


                sendMsg = "n_id="+ noti_id;
                osw.write(sendMsg);
                osw.flush();

                Log.i("공지사항삭제  시작", "에러");

                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("공지사항삭제  결과", "에러");
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
            Log.d("공지사항삭제 최종결과",s);
            Intent intent = new Intent(NoticeText.this, NoticeActivity.class);
            startActivity(intent);
        }
    }
}
