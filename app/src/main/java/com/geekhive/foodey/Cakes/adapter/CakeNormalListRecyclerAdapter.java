package com.geekhive.foodey.Cakes.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.Cakes.activities.CakeNormalCakeDetailActivity;
import com.geekhive.foodey.R;

import java.util.ArrayList;

public class CakeNormalListRecyclerAdapter extends RecyclerView.Adapter<CakeNormalListRecyclerAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    ArrayList<String> titlesNames;
    ArrayList<Integer> cakesImages;

    public CakeNormalListRecyclerAdapter(Context context, ArrayList<String> titlesNames, ArrayList<Integer> cakesImages) {
        this.titlesNames = titlesNames;
        this.cakesImages = cakesImages;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_layout_specialcake,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
     holder.cakeImg.setImageResource(cakesImages.get(position));
     holder.cakeName.setText(titlesNames.get(position));
        holder.cakeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, CakeNormalCakeDetailActivity.class);
                intent.putExtra("title",titlesNames.get(position));
                intent.putExtra("product_title",titlesNames.get(position));
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titlesNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView cakeImg;
        TextView cakeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cakeImg = (ImageView)itemView.findViewById(R.id.iv_cakeImg);
            cakeName = (TextView)itemView.findViewById(R.id.tv_cakeName);
        }
    }
}
