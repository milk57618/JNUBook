package com.example.myapplication.MyBook;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ReviewData;
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
import java.util.ArrayList;

//책 상세페이지
public class BookDetailActivity extends AppCompatActivity {

    ReviewAdapter r_adapter;
    ListView reviewList;
    Button bookdel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
       // ViewPager vp = findViewById(R.id.view);
       // SlideAdapter sd = new SlideAdapter(this);
       // vp.setAdapter(sd);

        //책 이름, 가격 인텐트 값으로 받아옴
        TextView dname = findViewById(R.id.d_title);
        TextView dprice = findViewById(R.id.d_price);

        String name = (BookMarketActivity.mList.get(BookMarketActivity.order)).getContent();
        dname.setText(name);
        String price = Integer.toString((BookMarketActivity.mList.get(BookMarketActivity.order)).getPrice());
        dprice.setText("가격: " + price);

        Button regi = findViewById(R.id.input_button);
        Button bookdel = findViewById(R.id.book_del);

        final EditText input = findViewById(R.id.input_review);
        TextView dmajor = findViewById(R.id.d_major);
        TextView dstate = findViewById(R.id.d_state);

        dmajor.setText("과목: "+ (BookMarketActivity.mList.get(BookMarketActivity.order)).getLect_name());

        dstate.setText("상태: "+ Integer.toString((BookMarketActivity.mList.get(BookMarketActivity.order)).getState())+"점");
         reviewList = findViewById(R.id.review);

         regi.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String contents = input.getText().toString();

                 comment temp = new comment(User.getInstance().getId(),"2018-06-15",contents);
                 r_adapter.addItem(temp.name, temp.date,temp.Comment);

                 //댓글 add


                 r_adapter.notifyDataSetChanged();

             }
         });

        if( Integer.parseInt(User.getInstance().getId()) != BookMarketActivity.mList.get(BookMarketActivity.order).getUser_id() ){
            bookdel.setVisibility(View.GONE);
        }

        //책삭제 시작
        bookdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DelBook delBook = new DelBook(BookMarketActivity.mList.get(BookMarketActivity.order).getB_id());
                delBook.execute();
            }
        });


        getComment comment= new getComment((BookMarketActivity.mList.get(BookMarketActivity.order)).getB_id());
        comment.execute();

    }
    private class ViewHolder{
        public ImageView r_Icon;
        public TextView r_name;
        public TextView r_review;
        public TextView r_date;
    }

    private class ReviewAdapter extends BaseAdapter {
        private Context context = null;
        private ArrayList<ReviewData> listreview = new ArrayList<ReviewData>();
        public ReviewAdapter(Context context){
            super();
            this.context = context;
        }
        @Override
        public int getCount() {
            return listreview.size();
        }
        @Override
        public Object getItem(int position) {
            return listreview.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.review_item, null);

                holder.r_Icon = convertView.findViewById(R.id.r_icon);
                holder.r_name = convertView.findViewById(R.id.r_name);
                holder.r_date = convertView.findViewById(R.id.r_date);
                holder.r_review = convertView.findViewById(R.id.r_review);

                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            ReviewData review = listreview.get(position);
            if(review.r_icon!=null){
                holder.r_Icon.setVisibility(View.VISIBLE);
                holder.r_Icon.setImageDrawable(review.r_icon);
            }else{
                holder.r_Icon.setVisibility(View.GONE);
            }
            holder.r_name.setText(review.r_name);
            holder.r_date.setText(review.r_date);
            holder.r_review.setText(review.r_review);

            return convertView;
        }
        public void addItem( String r_name, String r_date, String r_review ){
            ReviewData rd = null;
            rd = new ReviewData();

            rd.r_date = r_date;
            rd.r_name = r_name;
            rd.r_review = r_review;

            listreview.add(rd);
        }
    }

    //url : http://localhost:8080/jnuBookServer/Reply.jsp
    private class getComment extends AsyncTask<Void,Void,String> {

        private String url;
        private int b_id;
        private String sendMsg;
        private String receiveMsg;

        public getComment(int b_id){
            this.b_id = b_id;
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {

                String str;

                URL url = new URL("http://168.131.152.150:8080/jnuBookServer/Comment.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");

                Log.i("댓글통신 시작", "!!");

                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

                //sendMsg = "id="+strings[0]+"&pwd="+strings[1];
                sendMsg = "board_num="+ 1 +"&post_id="+ b_id;
                osw.write(sendMsg);
                osw.flush();

                Log.i("댓글통신 쓰기작업 시작", "에러");

                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", "에러");
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


            Log.d("댓글통신 결과", s);
            int res = 0;
            ArrayList<comment> commentList;

            commentList = new ArrayList<>();

            r_adapter = new ReviewAdapter(BookDetailActivity.this);
            reviewList.setAdapter(r_adapter);

            try {

                JSONArray jarray = new JSONArray(s);   // JSONArray 생


                for(int i=0; i < jarray.length(); i++){
                    JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                    Log.d("댓글통신 json작업시작","ㅇㅇ");
                    comment temp = new comment(jObject.getString("user_id"),jObject.getString("comment_date"),jObject.getString("content"));

                    commentList.add(temp);
                  }


            }catch (JSONException e){
                e.printStackTrace();
            }

            for(int i=0; i<commentList.size(); i++)
              r_adapter.addItem(commentList.get(i).name, commentList.get(i).date,commentList.get(i).Comment);


       }
    }

    public class comment {

        public  comment (String name,String date,String comment){
            this.name =name;
            this.date =date;
            this.Comment = comment;
        }

        public String name;
        public String date;
        public  String Comment;
    }




    ///삭제

    private class DelBook extends AsyncTask<Void,Void,String> {


        private int book_id;
        private String sendMsg;
        private String receiveMsg;

        public DelBook(int book_id){
            this.book_id = book_id;
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {

                String str;

                URL url = new URL("http://168.131.152.150:8080/jnuBookServer/DeleteBook.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                Log.i("책삭제 통신 시작", "!!");

                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");

                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());


                sendMsg = "b_id="+ book_id;
                osw.write(sendMsg);
                osw.flush();

                Log.i("책삭제   시작", "에러");

                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("책삭제   결과", "에러");
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
            Log.d("책삭제  최종결과",s);
            Intent intent = new Intent(BookDetailActivity.this, BookMarketActivity.class);
            startActivity(intent);
        }
    }
}

