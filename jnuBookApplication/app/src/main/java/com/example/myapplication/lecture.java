package com.example.myapplication;

/**
 * Created by ming on 2018-06-15.
 */

public class lecture {
    private int l_id;
    private String lecture_name;
    private String post_date;
    private String professor;
    private int user_id;
    private String lecture_content;

    public lecture(int l_id, String lecture_name, String post_date, String professor, String lecture_content, int user_id) {
        this.l_id = l_id;
        this.lecture_name = lecture_name;
        this.post_date = post_date;
        this.professor = professor;
        this.lecture_content = lecture_content;
        this.user_id=user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getL_id() {
        return l_id;
    }

    public void setL_id(int l_id) {
        this.l_id = l_id;
    }

    public String getLecture_name() {
        return lecture_name;
    }

    public void setLecture_name(String lecture_name) {
        this.lecture_name = lecture_name;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getLecture_content() {
        return lecture_content;
    }

    public void setLecture_content(String lecture_content) {
        this.lecture_content = lecture_content;
    }
}
