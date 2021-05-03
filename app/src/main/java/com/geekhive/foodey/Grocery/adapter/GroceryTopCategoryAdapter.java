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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Grocery.activities.GroceryShopStoreCategoryActivity;
import com.geekhive.foodey.Grocery.beans.grocerystorename.GroceryStoreNameList;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class GroceryTopCategoryAdapter extends RecyclerView.Adapter<GroceryTopCategoryAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    GroceryStoreNameList storeNameList;

    public GroceryTopCategoryAdapter(Context context, GroceryStoreNameList storeNameList) {
        this.mcontext = context;
        this.storeNameList = storeNameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_layout_topcategory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.categoryTitle.setText(storeNameList.getStore().get(position).getStore().getName());
        String imageUrl = WebServices.Foodey_Store_Image_URL + storeNameList.getStore().get(position).getStore().getImage();
        Picasso.get().load(imageUrl).error(R.drawable.shopstore).fit().into(holder.categoryImage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prefs.setStoreLat(mcontext, storeNameList.getStore().get(position).getStore().getLatitude());
                Prefs.setStoreLang(mcontext, storeNameList.getStore().get(position).getStore().getLongitude());
                Intent intent = new Intent(mcontext, GroceryShopStoreCategoryActivity.class);
                intent.putExtra("title", storeNameList.getStore().get(position).getStore().getName());
                intent.putExtra("store_id", storeNameList.getStore().get(position).getStore().getId());
                intent.putExtra("imageUrl", storeNameList.getStore().get(position).getStore().getImage());
                intent.putExtra("lati", storeNameList.getStore().get(position).getStore().getLatitude());
                intent.putExtra("longi", storeNameList.getStore().get(position).getStore().getLongitude());
                mcontext.startActivity(intent);
            }
        });
        holder.vT_adhg_time.setText("Time : " + storeNameList.getStore().get(position).getStore().getTiming());
    }

    @Override
    public int getItemCount() {
        if (storeNameList.getStore().size() > 5) {
            return 5;
        } else {
            return storeNameList.getStore().size();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryTitle,vT_adhg_time,vT_adhg_place;
        CardView cardView;
        LinearLayout ll_offer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = (ImageView) itemView.findViewById(R.id.iv_category);
            categoryTitle = (TextView) itemView.findViewById(R.id.tv_categoryText);
            cardView = itemView.findViewById(R.id.vC_adhg_card);
            vT_adhg_time = itemView.findViewById(R.id.vT_adhg_time);
            ll_offer = itemView.findViewById(R.id.ll_offer);
        }
    }
}
