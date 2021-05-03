package com.geekhive.foodey.Cakes.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.geekhive.foodey.R;


public class CakeSliderOfferAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    int slide2[];

    //Constructor Created

    public CakeSliderOfferAdapter(Context context, int[] slide2) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.slide2 = slide2;
    }

    @Override
    public int getCount() {

        return slide2.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View)object);  //object parameter
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        //LayoutInflater to use image
        View itemView = layoutInflater.inflate(R.layout.cake_layout_imageslider,container, false);
        ImageView imageView = (ImageView)itemView.findViewById(R.id.myImageView);
        imageView.setImageResource(slide2[position]);
        container.addView(itemView,0);
        return itemView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //container.removeView((View) object);
        ViewPager viewPager = (ViewPager)container;
        View view = (View)object;
        viewPager.removeView(view);
    }
}
