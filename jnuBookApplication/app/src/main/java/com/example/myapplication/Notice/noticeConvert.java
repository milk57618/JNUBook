package com.example.myapplication.Notice;

import android.util.Log;

import com.example.myapplication.Notice.notice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ming on 2018-06-07.
 */

public class noticeConvert {


    private static final String tag_n_id = "n_id";
    private static final String tag_post_date = "post_date";
    private static final String tag_title = "title";
    private static final String tag_content= "content";


    private ArrayList<notice> noticeList; //강의 목록 저장

    public void getData(String s){

        noticeList = new ArrayList<>();


        try {

            JSONArray jarray = new JSONArray(s);   // JSONArray 생성
            notice noticeItem;

            for(int i=0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                noticeItem = new notice(jObject.getInt(tag_n_id), jObject.getString(tag_post_date), jObject.getString(tag_title), jObject.getString(tag_content) );



                noticeList.add(noticeItem);
                Log.d("1과목: ",noticeList.get(i).getTitle() );
            }

            for(int i=0; i<noticeList.size();i++){
                Log.d("2과목: ", noticeList.get(i).getTitle() );
                // Log.d("과목: ",lectureList.get(i).getProf_name() );

            }

        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    public ArrayList<notice> getNoticeList() {
        return noticeList;
    }
}
