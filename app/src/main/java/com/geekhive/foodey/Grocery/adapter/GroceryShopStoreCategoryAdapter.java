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

import com.geekhive.foodey.Grocery.activities.GroceryStoreSubCategoryActivity;
import com.geekhive.foodey.Grocery.beans.grocerycategory.GroceryMainCategory;
import com.geekhive.foodey.R;

import java.util.ArrayList;

public class GroceryShopStoreCategoryAdapter extends RecyclerView.Adapter<GroceryShopStoreCategoryAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    GroceryMainCategory groceryMainCategory;
    ArrayList<Integer> serviceImage;


    public GroceryShopStoreCategoryAdapter(Context context, GroceryMainCategory groceryMainCategory, ArrayList<Integer> serviceImage) {
        this.mcontext = context;
        this.groceryMainCategory = groceryMainCategory;
        this.serviceImage = serviceImage;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_layout_shopstorecategory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
//     holder.categoryImage.setImageResource(groceryImages.get(position));
        holder.categoryTitle.setText(groceryMainCategory.getGroceryCategory().get(position).getCategoryType());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, GroceryStoreSubCategoryActivity.class);
//             intent.putExtra("title",groceryTitles.get(position).toString());
                intent.putExtra("title", groceryMainCategory.getGroceryCategory().get(position).getCategoryType());
                intent.putExtra("id", groceryMainCategory.getGroceryCategory().get(position).getId());
                intent.putExtra("store_id", groceryMainCategory.getGroceryCategory().get(position).getStoreId());
                mcontext.startActivity(intent);
            }
        });

        if (position % 2 == 1){
            holder.cardView.setBackgroundColor(mcontext.getResources().getColor(R.color.cpurple));
        } else {
            holder.cardView.setBackgroundColor(mcontext.getResources().getColor(R.color.corange));
        }

    }

    @Override
    public int getItemCount() {
        return groceryMainCategory.getGroceryCategory().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //ImageView categoryImage;
        TextView categoryTitle;
        CardView cardView;
        LinearLayout ll_category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = (TextView) itemView.findViewById(R.id.tv_shopNames);
            cardView = itemView.findViewById(R.id.card_viewMain);
        }
    }
}
