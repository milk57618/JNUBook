package com.example.myapplication;

import android.util.Log;

import com.example.myapplication.Notice.notice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ming on 2018-06-15.
 */

public class lectureConvert {

    private static final String tag_l_id="lect_id";
    private static final String tag_lecture_name="lect_name";
    private static final String tag_post_date = "post_date";
    private static final String tag_professor="prof_name";
    private static final String tag_lecture_content="content";
    private static final String tag_user_id = "user_id";

    private ArrayList<lecture> lectureList; //강의 목록 저장

    public void getData(String s){

        lectureList = new ArrayList<>();


        try {

            JSONArray jarray = new JSONArray(s);   // JSONArray 생성
            lecture lectureItem;

            for(int i=0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                lectureItem = new lecture(jObject.getInt(tag_l_id), jObject.getString(tag_lecture_name),jObject.getString(tag_post_date),
                        jObject.getString(tag_professor), jObject.getString(tag_lecture_content), jObject.getInt(tag_user_id));


                lectureList.add(lectureItem);
                Log.d("1과목: ",lectureList.get(i).getLecture_content() );
            }

            for(int i=0; i<lectureList.size();i++){
                Log.d("2과목: ", lectureList.get(i).getLecture_content() );
                // Log.d("과목: ",lectureList.get(i).getProf_name() );

            }

        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    public ArrayList<lecture> getLectureList() {
        return lectureList;
    }
}
