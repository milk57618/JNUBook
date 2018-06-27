package com.example.myapplication;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class SlideAdapter extends PagerAdapter {
    private int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3};
    private LayoutInflater inflater;
    private Context context;

    public SlideAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.slider, container, false);
        ImageView imageView = v.findViewById(R.id.imageview);
        imageView.setImageResource(images[position]);
        container.addView(v);
        return v;
    }
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }
}