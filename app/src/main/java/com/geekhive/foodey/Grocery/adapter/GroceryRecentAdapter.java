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

import com.geekhive.foodey.Grocery.activities.GroceryDetailActivity;
import com.geekhive.foodey.Grocery.beans.recentpurchase.RecentPurchase;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class GroceryRecentAdapter extends RecyclerView.Adapter<GroceryRecentAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    RecentPurchase recentPurchase;

    public GroceryRecentAdapter(Context context, RecentPurchase recentPurchase) {
        this.mcontext = context;
        this.recentPurchase = recentPurchase;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_layout_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        String url = WebServices.Foodey_Grocery_Image +
                recentPurchase.getRecentPurchase().getGCartDetail().get(position).get(0).getGrocery().getImage();
        if (!url.equals("")) {
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.productImage);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recentPurchase.getRecentPurchase().getStore().get(position).getId() != null){
                    Intent intent = new Intent(mcontext, GroceryDetailActivity.class);
                    intent.putExtra("title", recentPurchase.getRecentPurchase().getGCartDetail().get(position).get(0).getGrocery().getProductName());
                    intent.putExtra("id", recentPurchase.getRecentPurchase().getGCartDetail().get(position).get(0).getGrocery().getId());
                    intent.putExtra("price", recentPurchase.getRecentPurchase().getGCartDetail().get(position).get(0).getGrocery().getPrice());
                    intent.putExtra("description", recentPurchase.getRecentPurchase().getGCartDetail().get(position).get(0).getGrocery().getProductDescription());
                    intent.putExtra("mrp", recentPurchase.getRecentPurchase().getGCartDetail().get(position).get(0).getGrocery().getMrp());
                    intent.putExtra("quantity", recentPurchase.getRecentPurchase().getGCartDetail().get(position).get(0).getGrocery().getQuantity());
                    intent.putExtra("store_id", recentPurchase.getRecentPurchase().getGCartDetail().get(position).get(0).getGrocery().getStoreId());
                    intent.putExtra("product_id", recentPurchase.getRecentPurchase().getGCartDetail().get(position).get(0).getGrocery().getId());
                    intent.putExtra("url", recentPurchase.getRecentPurchase().getGCartDetail().get(position).get(0).getGrocery().getImage());
                    mcontext.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return recentPurchase.getRecentPurchase().getGCartDetail().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.iv_vegImage);
            cardView = (CardView) itemView.findViewById(R.id.card_viewMain);
        }
    }
}
