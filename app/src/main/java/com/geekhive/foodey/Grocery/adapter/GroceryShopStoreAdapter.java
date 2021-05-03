package com.geekhive.foodey.Grocery.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.Grocery.activities.GroceryShopStoreCategoryActivity;
import com.geekhive.foodey.Grocery.beans.grocerystorename.GroceryStoreNameList;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class GroceryShopStoreAdapter extends RecyclerView.Adapter<GroceryShopStoreAdapter.ViewHolder> {

    //Variables
    private Context context;
    GroceryStoreNameList storeNameList;

    public GroceryShopStoreAdapter(Context context, GroceryStoreNameList storeNameList) {
        this.context = context;
        this.storeNameList = storeNameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_layout_shopstore,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
//        holder.shopImages.setImageResource(shopImages.get(position));
        holder.shopName.setText(storeNameList.getStore().get(position).getStore().getName());
        holder.shopAddress.setText(storeNameList.getStore().get(position).getStore().getAddress()+"..");
        String imageUrl = WebServices.Foodey_Store_Image_URL + storeNameList.getStore().get(position).getStore().getImage();
        Picasso.get().load(imageUrl).error(R.drawable.shopstore).fit().placeholder(R.drawable.shopstore).into(holder.shopImages);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GroceryShopStoreCategoryActivity.class);
                intent.putExtra("title",storeNameList.getStore().get(position).getStore().getName());
                intent.putExtra("store_id",storeNameList.getStore().get(position).getStore().getId());
                intent.putExtra("imageUrl", storeNameList.getStore().get(position).getStore().getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return storeNameList.getStore().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView shopImages;
        TextView shopName,shopAddress;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shopImages = (ImageView) itemView.findViewById(R.id.iv_shopName);
            shopName = (TextView)itemView.findViewById(R.id.tv_shopName);
            shopAddress = (TextView)itemView.findViewById(R.id.tv_shopAddress);
            cardView = itemView.findViewById(R.id.card_view_shop);
        }
    }
}
