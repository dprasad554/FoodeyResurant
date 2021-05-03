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

public class CakeTopCategoryAdapter extends RecyclerView.Adapter<CakeTopCategoryAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    CakeStoreList storeNameList;
    String url = "";

    public CakeTopCategoryAdapter(Context context, CakeStoreList storeNameList) {
        this.mcontext = context;
        this.storeNameList = storeNameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_layout_topcategory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        url = WebServices.Foodey_Cakes_Image_Store + storeNameList.getCakeStore().get(position).getCakeStore().getImage();
        holder.categoryTitle.setText(storeNameList.getCakeStore().get(position).getCakeStore().getName());
        if (!url.equals("")){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.categoryImage);
        }

        holder.categoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, CakeShopSubCategoryActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title",storeNameList.getCakeStore().get(position).getCakeStore().getName());
                intent.putExtra("storeid",storeNameList.getCakeStore().get(position).getCakeStore().getId());
                intent.putExtra("lati",storeNameList.getCakeStore().get(position).getCakeStore().getLatitude());
                intent.putExtra("longi",storeNameList.getCakeStore().get(position).getCakeStore().getLongitude());
                mcontext.startActivity(intent);
            }
        });
        if (storeNameList.getCakeStore().get(position).getCakeOffer().size() != 0){
            holder.tv_offer.setText(storeNameList.getCakeStore().get(position).getCakeOffer().get(0).getDiscount()+" % Discount");
        }

    }

    @Override
    public int getItemCount() {
        if (storeNameList.getCakeStore().size() > 6) {
            return 6;
        } else {
            return storeNameList.getCakeStore().size();
        }


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryTitle,vT_adhg_time,tv_offer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = (ImageView) itemView.findViewById(R.id.iv_category);
            categoryTitle = (TextView) itemView.findViewById(R.id.tv_categoryText);
            tv_offer = (TextView)itemView.findViewById(R.id.vT_offer_txt);
           // vT_adhg_time = (TextView) itemView.findViewById(R.id.vT_adhg_time);
        }
    }
}
