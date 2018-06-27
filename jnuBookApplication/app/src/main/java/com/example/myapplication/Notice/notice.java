package com.example.myapplication.Notice;

/**
 * Created by ming on 2018-06-07.
 */
public class notice {

    private int n_id;
    private String post_date;
    private String title;
    private String content;

    public notice(int n_id,String post_date,String title, String content){
        this.n_id = n_id;
        this.post_date = post_date;
        this.title = title;
        this.content = content;
    }

    public int getN_id() {
        return n_id;
    }
    public void setN_id(int n_id) {
        this.n_id = n_id;
    }
    public String getPost_date() {
        return post_date;
    }
    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}