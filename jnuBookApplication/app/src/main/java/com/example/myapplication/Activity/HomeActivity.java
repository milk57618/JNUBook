package com.example.myapplication.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.LectureActivity;
import com.example.myapplication.MyBook.BookMarketActivity;
import com.example.myapplication.Notice.NoticeActivity;
import com.example.myapplication.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton noti = (ImageButton)findViewById(R.id.noticeBtn);
        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });

        ImageButton sett = (ImageButton)findViewById(R.id.settingBtn);
        sett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        ImageButton book = (ImageButton)findViewById(R.id.boardBtn);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BookMarketActivity.class);
                startActivity(intent);
            }
        });

        ImageButton lect = (ImageButton)findViewById(R.id.board2Btn);
        lect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LectureActivity.class);
                startActivity(intent);
            }
        });

        ImageButton jnuBtn =(ImageButton) findViewById(R.id.jnuBtn);
        jnuBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jnu.ac.kr")));
            }
        });

        ImageButton portalBtn =(ImageButton) findViewById(R.id.portalBtn);
        portalBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://portal.jnu.ac.kr")));
            }
        });

        ImageButton eclassBtn =(ImageButton) findViewById(R.id.eclassBtn);
        eclassBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://eclass.jnu.ac.kr")));
            }
        });


    }
}
