package com.example.myapplication.Activity;

import android.content.Intent;
import android.net.Network;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


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

public class LoginActivity extends AppCompatActivity {

    private String userId;
    private String userPw;
    Button lg;
    Button jn;
    EditText id_T;
    EditText pw_T;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lg = findViewById(R.id.loginBtn);
        id_T = findViewById(R.id.idText);
        pw_T = findViewById(R.id.pwText);
        jn = findViewById(R.id.joinBtn1);

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userId = id_T.getText().toString();
                userPw = pw_T.getText().toString();

                if (!userId.equals("") && !userPw.equals("")) {
                    loginStart login = new  loginStart(userId,userPw);
                    login.execute();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("아이디 비번을 입력해주세요.")
                            .setNegativeButton("확인", null)
                            .create()
                            .show();
                }
            }
        });


        jn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(LoginActivity.this, JoinActivity.class);
                Log.d("결과", "!!");
                startActivity(intent2);

            }
        });
    }

    private class loginStart extends AsyncTask<Void,Void,String> {

        private String url;
        private String u_id;
        private String u_pw;
        private String sendMsg;
        private String receiveMsg;

        public loginStart(String id, String pw){
            this.u_id = id;
            this.u_pw = pw;
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {

                String str;

                URL url = new URL("http://168.131.152.150:8080/jnuBookServer/Login.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");

                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

                //sendMsg = "id="+strings[0]+"&pwd="+strings[1];
                sendMsg = "user_id="+ u_id +"&user_pw="+ u_pw;
                osw.write(sendMsg);
                osw.flush();

                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
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


            Log.d("결과", s);
            int res = 1;

            try {

                JSONArray jarray = new JSONArray(s);   // JSONArray 생


                for(int i=0; i < jarray.length(); i++){
                    JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                    User user = User.getInstance();
                    user.set_Uesr(jObject.getString("user_pw"),jObject.getString("user_name"),jObject.getString("user_id"),jObject.getBoolean("user_gender"),
                            jObject.getInt("user_grade"),jObject.getBoolean("adminChecked"));
                    res = 1;
                }

            }catch (JSONException e){
                e.printStackTrace();
            }

            if(res == 1) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage("로그인에 실패하였습니다.")
                        .setNegativeButton("다시시도", null)
                        .create()
                        .show();
            }
        }
    }

}


