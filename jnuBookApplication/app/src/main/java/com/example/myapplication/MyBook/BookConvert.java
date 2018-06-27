package com.example.myapplication.MyBook;

import android.util.Log;

import com.example.myapplication.Notice.notice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ming on 2018-06-07.
 */

public class BookConvert {

    private static final String tag_b_id = "b_id";
    private static final String tag_b_name = "b_name";
    private static final String tag_lect_name = "lect_name";
    private static final String tag_major_id = "magor_id";
    private static final String tag_grade = "grade";
    private static final String tag_title = "title";
    private static final String tag_content = "content";
    private static final String tag_state = "state";
    private static final String tag_user_id = "user_id";
    private static final String tag_post_date = "post_date";
    private static final String tag_price = "price";
//"b_name":"Operating System Concepts","b_id":1,"magor_id":26,"user_id":150000,"post_date":"2018-06-04 00:00:00.0","price":0,"grade":3,"state":5,"title":"운영체제 책 공유합니다.","lect_name":"운영체제","content":"책공유 연락주세요"
//"b_name" "b_id" "magor_id" ,"user_id" ,"post_date":"2018-06-04 00:00:00.0","price":0,"grade":3,"state":5,"title":"운영체제 책 공유합니다.","lect_name":"운영체제","content":"책공유 연락주세요"

    private ArrayList<Book> booksList; //강의 목록 저장

    public void getData(String s){

        booksList = new ArrayList<>();


        try {

            JSONArray jarray = new JSONArray(s);   // JSONArray 생성
             Book BookItem;

            for(int i=0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                Log.d("책: ",jObject.getString(tag_b_name));

                BookItem = new Book(jObject.getInt(tag_b_id), jObject.getString(tag_b_name), jObject.getString(tag_lect_name), jObject.getInt(tag_major_id),
                        jObject.getInt(tag_grade), jObject.getString(tag_title), jObject.getString(tag_content), jObject.getInt(tag_state) ,
                        jObject.getInt(tag_user_id) , jObject.getString(tag_post_date),jObject.getInt(tag_price)
                );

                booksList.add(BookItem);
                //Log.d("1과목: ",noticeList.get(i).getTitle() );
            }

           for(int i=0; i<booksList.size();i++){
                Log.d("2과목: ", booksList.get(i).getPost_date());
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    public ArrayList<Book> getBooksList() {
        return booksList;
    }
}
