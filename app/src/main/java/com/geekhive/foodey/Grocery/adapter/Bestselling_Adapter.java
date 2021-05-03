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

import com.geekhive.foodey.Grocery.activities.GroceryDetailActivity;
import com.geekhive.foodey.Grocery.beans.grocerybestselling.GroceryBestSelling;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class Bestselling_Adapter extends RecyclerView.Adapter<Bestselling_Adapter.ViewHolder> {

    //Variables
    private Context mcontext;
    GroceryBestSelling groceryBestSelling;

    public Bestselling_Adapter(Context context,GroceryBestSelling groceryBestSelling) {
        this.mcontext = context;
        this.groceryBestSelling = groceryBestSelling;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.best_selling_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.categoryTitle.setText(groceryBestSelling.getGroceryList().getGrocery().get(position).getProductName());
        if(!groceryBestSelling.getGroceryList().getGrocery().get(position).getCashback().equals("0")){
            holder.ll_offer.setVisibility(View.VISIBLE);
            holder.cashback.setText("Rs "+groceryBestSelling.getGroceryList().getGrocery().get(position).getCashback()+" cashback");
        }else {
            holder.ll_offer.setVisibility(View.GONE);
        }

        String imageUrl = WebServices.Foodey_Grocery_Image + groceryBestSelling.getGroceryList().getGrocery().get(position).getImage();
        Picasso.get().load(imageUrl).error(R.drawable.shopstore).fit().placeholder(R.drawable.shopstore).into(holder.categoryImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, GroceryDetailActivity.class);
               // intent.putExtra("title", storeNameList.getStore().get(position).getName());
                intent.putExtra("store_id", groceryBestSelling.getGroceryList().getGrocery().get(position).getStoreId());
                intent.putExtra("title",groceryBestSelling.getGroceryList().getGrocery().get(position).getProductName());
                intent.putExtra("id",  groceryBestSelling.getGroceryList().getGrocery().get(position).getId());
                intent.putExtra("price",  groceryBestSelling.getGroceryList().getGrocery().get(position).getPrice());
                intent.putExtra("description",  groceryBestSelling.getGroceryList().getGrocery().get(position).getProductDescription());
                intent.putExtra("mrp",  groceryBestSelling.getGroceryList().getGrocery().get(position).getMrp());
                intent.putExtra("quantity",  groceryBestSelling.getGroceryList().getGrocery().get(position).getQuantity());
                //intent.putExtra("store_id", recentPurchase.getRecentPurchase().getGCartDetail().get(position).get(0).getGrocery().getStoreId());
                intent.putExtra("product_id", groceryBestSelling.getGroceryList().getGrocery().get(position).getId());
                intent.putExtra("url",  groceryBestSelling.getGroceryList().getGrocery().get(position).getImage());
                //intent.putExtra("imageUrl", storeNameList.getStore().get(position).getImage());
                mcontext.startActivity(intent);
            }
        });
      //  holder.vT_adhg_time.setText(storeNameList.getStore().get(position).getDate());
    }

    @Override
    public int getItemCount() {

        return groceryBestSelling.getGroceryList().getGrocery().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryTitle,vT_adhg_time,cashback;
        CardView cardView;
        LinearLayout ll_offer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = (ImageView) itemView.findViewById(R.id.iv_category);
            categoryTitle = (TextView) itemView.findViewById(R.id.tv_categoryText);
            cardView = itemView.findViewById(R.id.vC_adhg_card);
            vT_adhg_time = itemView.findViewById(R.id.vT_adhg_time);
            cashback = itemView.findViewById(R.id.cashback);
            ll_offer = itemView.findViewById(R.id.ll_offer);

        }
    }
}
