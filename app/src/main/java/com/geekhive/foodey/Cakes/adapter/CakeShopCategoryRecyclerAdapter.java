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

import com.geekhive.foodey.Cakes.activities.CakeShopSubCategoryActivity;
import com.geekhive.foodey.R;

import java.util.ArrayList;

public class CakeShopCategoryRecyclerAdapter extends RecyclerView.Adapter<CakeShopCategoryRecyclerAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    ArrayList<String> shopNames;
    ArrayList<String> shopAddress;
    ArrayList<Integer> shopImages;

    public CakeShopCategoryRecyclerAdapter(Context context, ArrayList<String> shopNames, ArrayList<String> shopAddress,
                                           ArrayList<Integer> shopImages) {
        this.shopNames = shopNames;
        this.shopAddress = shopAddress;
        this.shopImages = shopImages;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_layout_shopstore,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     holder.shopname.setText(shopNames.get(position));
     holder.shopAddress.setText(shopAddress.get(position));
     holder.shopimage.setImageResource(shopImages.get(position));
     holder.shopimage.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent =  new Intent(mcontext, CakeShopSubCategoryActivity.class);
             mcontext.startActivity(intent);
         }
     });
    }

    @Override
    public int getItemCount() {
        return shopImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView shopimage;
        TextView shopname,shopAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shopimage = (ImageView)itemView.findViewById(R.id.iv_shopImage);
            shopname = (TextView)itemView.findViewById(R.id.tv_shopName);
            shopAddress = (TextView)itemView.findViewById(R.id.tv_shopDelivery);

        }
    }
}
