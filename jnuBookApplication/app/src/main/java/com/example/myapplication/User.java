package com.example.myapplication;

/**
 * Created by ming on 2018-06-14.
 */

public class User {
        private String user_pw;
        private  String user_name;
        private  String user_id;
        private  boolean user_gender;
        private  int user_grade;
        private  boolean admin_check;

        private User () {}
        private static class Singleton {
            private static final User instance = new User();
        }

        public static User getInstance () {

            return Singleton.instance;
        }

        public void set_Uesr(String pw,String name,String id, boolean gender,int grade, boolean admin_check){
            this.user_pw = pw;
            this.user_name = name;
            this.user_id = id;
            this.user_gender = gender;
            this.user_grade = grade;
            this.admin_check = admin_check;
        }

        public String getName(){
            return this.user_name;
        }

        public String getId(){

             return this.user_id;
       }

       public int getGrade(){
            return  this.user_grade;
       }
       public  boolean getAdmincheck() { return this.admin_check; }
}
