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

import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Grocery.activities.GroceryShopProductListActivity;
import com.geekhive.foodey.Grocery.beans.grocerysubcategory.GrocerySubCategory;
import com.geekhive.foodey.R;

public class GroceryShopStoreSubCategoryAdapter extends RecyclerView.Adapter<GroceryShopStoreSubCategoryAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    GrocerySubCategory grocerySubCategory;


    //ArrayList<Integer> serviceImage;

    public GroceryShopStoreSubCategoryAdapter(Context context, GrocerySubCategory grocerySubCategory) {
        this.mcontext = context;
        this.grocerySubCategory = grocerySubCategory;

        //this.serviceImage = serviceImage;
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
        holder.categoryTitle.setText(grocerySubCategory.getProductSubCategory().get(position).getSubCategoryName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, GroceryShopProductListActivity.class);

//             intent.putExtra("title",groceryTitles.get(position).toString());
                intent.putExtra("title", grocerySubCategory.getProductSubCategory().get(position).getSubCategoryName());
                intent.putExtra("sub_id", grocerySubCategory.getProductSubCategory().get(position).getId());
                intent.putExtra("lati", Prefs.getUserLat(mcontext));
                intent.putExtra("longi", Prefs.getUserLang(mcontext));


                //   intent.putExtra("store_id",storeNameList.getStore().get(0).getStore().getId());
                //storeid= storeNameList.getStore().get(0).getStore().getId();


                mcontext.startActivity(intent);
            }
        });
        if (position % 2 == 1) {
            holder.cardView.setCardBackgroundColor(mcontext.getResources().getColor(R.color.corange));
        } else  {
            holder.cardView.setCardBackgroundColor(mcontext.getResources().getColor(R.color.cblue));
        }
        // holder.categoryImage.setImageResource(serviceImage.get(position));
    }

    @Override
    public int getItemCount() {
        return grocerySubCategory.getProductSubCategory().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /*ImageView categoryImage;*/
        TextView categoryTitle;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //categoryImage = (ImageView)itemView.findViewById(R.id.iv_shopImages);
            categoryTitle = (TextView) itemView.findViewById(R.id.tv_shopNames);
            cardView = itemView.findViewById(R.id.card_viewMain);
        }
    }
}
