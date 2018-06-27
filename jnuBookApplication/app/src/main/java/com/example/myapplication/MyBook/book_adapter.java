package com.example.myapplication.MyBook;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import java.util.ArrayList;


public class book_adapter extends BaseAdapter {

    private Context context;
    private ArrayList<Book> mList;

    public book_adapter(Context context , ArrayList<Book> books){

        this.context = context;
        mList = books;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.item_book, null);
        TextView bookText = (TextView) v.findViewById(R.id.bookTitle);
        TextView nameText = (TextView) v.findViewById(R.id.bookUser);
        TextView dateText = (TextView) v.findViewById(R.id.bookDate);
        Log.d("!!!!","UI등록 완료");

        bookText.setText(mList.get(i).getTitle());
        nameText.setText(Integer.toString(mList.get(i).getUser_id()));
        dateText.setText(mList.get(i).getPost_date());
        Log.d(mList.get(i).getPost_date(),"내용등록 완료");
        v.setTag(mList.get(i).getContent());
        return v;
    }
}

