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
import com.geekhive.foodey.Cakes.beans.cakestore.CakeStoreList;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class CakeShopStoreRecyclerAdapter extends RecyclerView.Adapter<CakeShopStoreRecyclerAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    CakeStoreList cakeStoreList;
    String url = "";


    public CakeShopStoreRecyclerAdapter(Context context, CakeStoreList cakeStoreList) {

        this.mcontext = context;
        this.cakeStoreList = cakeStoreList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_layout_shopstore, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.shopname.setText(cakeStoreList.getCakeStore().get(position).getCakeStore().getName());
        holder.shopAddress.setText(cakeStoreList.getCakeStore().get(position).getCakeStore().getAddress());
        holder.tv_shopTime.setText(cakeStoreList.getCakeStore().get(position).getCakeStore().getDate());
        if (cakeStoreList.getCakeStore().get(position).getCakeStore().getImage() != null) {
            url = WebServices.Foodey_Cakes_Image_Store + cakeStoreList.getCakeStore().get(position).getCakeStore().getImage();
        }
        if (!url.equals("")) {
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.shopimage);
        }
        holder.shopimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, CakeShopSubCategoryActivity.class);
                intent.putExtra("url", cakeStoreList.getCakeStore().get(position).getCakeStore().getImage());
                intent.putExtra("title", cakeStoreList.getCakeStore().get(position).getCakeStore().getName());
                intent.putExtra("storeid", cakeStoreList.getCakeStore().get(position).getCakeStore().getId());
                mcontext.startActivity(intent);
            }
        });
        if (cakeStoreList.getCakeStore().get(position).getCakeOffer().size() != 0){
            holder.tv_offer.setVisibility(View.VISIBLE);
            holder.tv_offer.setText(cakeStoreList.getCakeStore().get(position).getCakeOffer().get(0).getDiscount() + " % Discount " + cakeStoreList.getCakeStore().get(position).getCakeOffer().get(0).getName());
        } else {
            holder.tv_offer.setVisibility(View.GONE);
            holder.vI_cpn.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return cakeStoreList.getCakeStore().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView shopimage,vI_cpn;
        TextView shopname, shopAddress, tv_shopTime, tv_offer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shopimage = (ImageView) itemView.findViewById(R.id.iv_shopImage);
            shopname = (TextView) itemView.findViewById(R.id.tv_shopName);
            shopAddress = (TextView) itemView.findViewById(R.id.tv_shopDelivery);
            tv_shopTime = (TextView) itemView.findViewById(R.id.tv_shopTime);
            tv_offer = (TextView) itemView.findViewById(R.id.vT_offer_txt);
            vI_cpn = (ImageView)itemView.findViewById(R.id.vI_cpn);
        }
    }
}
