package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import java.util.List;

/**
 * Created by ming on 2018-06-15.
 */

public class lecture_adapter extends BaseAdapter {

    private Context context;
    private List<lecture> lectureList;

    public lecture_adapter(Context context, List<lecture> lectureList) {
        this.context = context;
        this.lectureList = lectureList;
    }

    @Override
    public int getCount() {
        return lectureList.size();
    }

    @Override
    public Object getItem(int i) {
        return lectureList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.lecture_context, null);
        TextView LecTitle = (TextView) v.findViewById(R.id.LecTitle);
        TextView LecUser = (TextView) v.findViewById(R.id.LecUser);
        TextView LecDate = (TextView) v.findViewById(R.id.LecDate);

        LecTitle.setText(lectureList.get(i).getLecture_name());
        LecUser.setText(Integer.toString(lectureList.get(i).getUser_id()));
        LecDate.setText(lectureList.get(i).getPost_date());

        v.setTag(lectureList.get(i).getLecture_content());
        return v;
    }

}
