package com.example.myapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.User;

public class SettingActivity extends AppCompatActivity {

    TextView userId;
    TextView userName;
    TextView userGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button btn = findViewById(R.id.logoutBtn);
        userId = findViewById(R.id.idText);
        userName = findViewById(R.id.nameText);
        userGrade = findViewById(R.id.gradeText);

        userId.setText(User.getInstance().getName());
        userName.setText(User.getInstance().getId());
        userGrade.setText(Integer.toString(User.getInstance().getGrade()) + " 학년");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
