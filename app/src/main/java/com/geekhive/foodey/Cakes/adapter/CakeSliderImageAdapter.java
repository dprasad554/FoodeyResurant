package com.geekhive.foodey.Cakes.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.geekhive.foodey.Cakes.beans.cakeslider.Cakeslider;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;


public class CakeSliderImageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    Cakeslider cakeslider;

    //Constructor Created

    public CakeSliderImageAdapter(Context context, Cakeslider cakeslider) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.cakeslider = cakeslider;
    }

    @Override
    public int getCount() {

        return cakeslider.getCSlider().size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View)object);  //object parameter
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        String url = "";
        //LayoutInflater to use image
        View itemView = layoutInflater.inflate(R.layout.cake_layout_imageslider,container, false);

        ImageView imageView = (ImageView)itemView.findViewById(R.id.myImageView);
        //imageView.setImageResource(slide[position]);
        container.addView(itemView,0);
        if(cakeslider.getCSlider().get(position).getImage() != null) {
            url = WebServices.Foodey_Cakes_Image_Slider_URL + cakeslider.getCSlider().get(position).getImage();
        }
        if (!url.equals("")){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo)//if failed
                    .into(imageView);
        }
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
