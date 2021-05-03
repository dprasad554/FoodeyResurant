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
import com.geekhive.foodey.Cakes.beans.cakestorebycategory.StorebyCategory;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class CakeStorebycategory  extends RecyclerView.Adapter<CakeStorebycategory.ViewHolder> {

    //Variables
    private Context mcontext;
    StorebyCategory storebyCategory;
    String url = "";


    public CakeStorebycategory(Context context, StorebyCategory storebyCategory) {

        this.mcontext = context;
        this.storebyCategory = storebyCategory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_layout_shopstore,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.shopname.setText(storebyCategory.getCakeStore().get(position).getCakeStore().getName());
        holder.shopAddress.setText(storebyCategory.getCakeStore().get(position).getCakeStore().getAddress());
        holder.tv_shopTime.setText(storebyCategory.getCakeStore().get(position).getCakeStore().getDate());
        if(storebyCategory.getCakeStore().get(position).getCakeStore().getImage() != null) {
            url = WebServices.Foodey_Grocery_Image_URL + storebyCategory.getCakeStore().get(position).getCakeStore().getImage();
        }
        if (!url.equals("")){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.shopimage);
        }
        holder.shopimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(mcontext, CakeShopSubCategoryActivity.class);
                intent.putExtra("url",storebyCategory.getCakeStore().get(position).getCakeStore().getImage());
                intent.putExtra("title",storebyCategory.getCakeStore().get(position).getCakeStore().getName());
                intent.putExtra("storeid",storebyCategory.getCakeStore().get(position).getCakeStore().getId());
                mcontext.startActivity(intent);
            }
        });
       // holder.tv_offer.setText(storebyCategory.getCakeStore().get(position).getCakeOffer().get(position).getDiscount()+" % Discount "+cakeStoreList.getCakeStore().get(position).getCakeOffer().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return storebyCategory.getCakeStore().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView shopimage;
        TextView shopname,shopAddress,tv_shopTime,tv_offer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shopimage = (ImageView)itemView.findViewById(R.id.iv_shopImage);
            shopname = (TextView)itemView.findViewById(R.id.tv_shopName);
            shopAddress = (TextView)itemView.findViewById(R.id.tv_shopDelivery);
            tv_shopTime = (TextView)itemView.findViewById(R.id.tv_shopTime);
            tv_offer = (TextView)itemView.findViewById(R.id.vT_offer_txt);
        }
    }
}
