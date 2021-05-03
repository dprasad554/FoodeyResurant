package com.geekhive.foodey.Grocery.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.Grocery.beans.grocerystorename.GroceryStoreNameList;
import com.geekhive.foodey.R;

public class NewlunchesAdapter extends RecyclerView.Adapter<NewlunchesAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    GroceryStoreNameList storeNameList;

    public NewlunchesAdapter(Context context) {
        this.mcontext = context;
        this.storeNameList = storeNameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_lunches_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       /* holder.categoryTitle.setText(storeNameList.getStore().get(position).getName());
        String imageUrl = "http://foodeyservices.com/img/store/" + storeNameList.getStore().get(position).getImage();
        Picasso.get().load(imageUrl).error(R.drawable.shopstore).fit().placeholder(R.drawable.shopstore).into(holder.categoryImage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, GroceryShopStoreCategoryActivity.class);
                intent.putExtra("title", storeNameList.getStore().get(position).getName());
                intent.putExtra("store_id", storeNameList.getStore().get(position).getId());
                intent.putExtra("imageUrl", storeNameList.getStore().get(position).getImage());
                mcontext.startActivity(intent);
            }
        });
        holder.vT_adhg_time.setText(storeNameList.getStore().get(position).getDate());*/
    }

    @Override
    public int getItemCount() {
            return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryTitle,vT_adhg_time;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = (ImageView) itemView.findViewById(R.id.iv_category);
            categoryTitle = (TextView) itemView.findViewById(R.id.tv_categoryText);
            cardView = itemView.findViewById(R.id.vC_adhg_card);
            vT_adhg_time = itemView.findViewById(R.id.vT_adhg_time);
        }
    }
}
