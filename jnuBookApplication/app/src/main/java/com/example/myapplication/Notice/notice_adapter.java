package com.example.myapplication.Notice;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.Notice.notice;
import com.example.myapplication.R;

import java.util.List;

/**
 * Created by ming on 2018-06-06.
 */

public class notice_adapter extends BaseAdapter {

    private Context context;
    private List<notice> noticeList;

    public notice_adapter(Context context, List<notice> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @Override
    public int getCount() {
        return noticeList.size();
    }

    @Override
    public Object getItem(int i) {
        return noticeList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.notice_context, null);
        TextView noticeText = (TextView) v.findViewById(R.id.noticeText);
        TextView nameText = (TextView) v.findViewById(R.id.nameText);
        TextView dateText = (TextView) v.findViewById(R.id.dateText);

        noticeText.setText(noticeList.get(i).getTitle());
        nameText.setText("JNUworld");
        dateText.setText(noticeList.get(i).getPost_date());

        v.setTag(noticeList.get(i).getContent());
        return v;
    }
}
