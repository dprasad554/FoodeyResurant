package com.geekhive.foodey.Grocery.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Grocery.activities.GroceryDetailActivity;
import com.geekhive.foodey.Grocery.beans.grocerybestselling.GroceryBestSelling;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class GroceryBestSellingListAdapter extends RecyclerView.Adapter<GroceryBestSellingListAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    GroceryBestSelling groceryBestSelling;
    int minteger = 0;

    public GroceryBestSellingListAdapter(Context context, GroceryBestSelling groceryBestSelling ) {
        this.mcontext = context;
        this.groceryBestSelling = groceryBestSelling;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_layout_productlistbestselling, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        String url = WebServices.Foodey_Grocery_Image +
                groceryBestSelling.getGroceryList().getGrocery().get(position).getImage();
        if (!url.equals("")){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.productImage);
        }
        holder.groceryName.setText(groceryBestSelling.getGroceryList().getGrocery().get(position).getProductName());
        holder.groceryPriceSave.setText("Rs. "+groceryBestSelling.getGroceryList().getGrocery().get(position).getPrice());
        holder.groceryPriceCut.setText("Mrp. "+groceryBestSelling.getGroceryList().getGrocery().get(position).getMrp());
        holder.productQTY.setText("Quantity: "+groceryBestSelling.getGroceryList().getGrocery().get(position).getQuantity()+
                groceryBestSelling.getGroceryList().getGrocery().get(position).getQuantityDetail());
        holder.linearProductDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, GroceryDetailActivity.class);
                intent.putExtra("title",groceryBestSelling.getGroceryList().getGrocery().get(position).getProductName());
                intent.putExtra("id",groceryBestSelling.getGroceryList().getGrocery().get(position).getId());
                intent.putExtra("price",groceryBestSelling.getGroceryList().getGrocery().get(position).getPrice());
                intent.putExtra("description",groceryBestSelling.getGroceryList().getGrocery().get(position).getProductDescription());
                intent.putExtra("mrp",groceryBestSelling.getGroceryList().getGrocery().get(position).getMrp());
                intent.putExtra("quantity",groceryBestSelling.getGroceryList().getGrocery().get(position).getQuantity());
                intent.putExtra("store_id",groceryBestSelling.getGroceryList().getGrocery().get(position).getStoreId());
                intent.putExtra("product_id",groceryBestSelling.getGroceryList().getGrocery().get(position).getId());
                intent.putExtra("url",groceryBestSelling.getGroceryList().getGrocery().get(position).getImage());

                mcontext.startActivity(intent);
            }
        });

        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.increaseInteger(view);
            }
        });

        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.decreaseInteger(view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groceryBestSelling.getGroceryList().getGrocery().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView groceryName, groceryNameTag, groceryPriceSave, groceryPriceCut, productQTY;
        Button increase, decrease;
        LinearLayout linearProductDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.iv_productImage);
            groceryName = itemView.findViewById(R.id.tv_productName);
            groceryNameTag = itemView.findViewById(R.id.tv_productNameTag);
            groceryPriceSave = itemView.findViewById(R.id.tv_productPriceSave);
            groceryPriceCut = itemView.findViewById(R.id.tv_productPriceCut);
            groceryPriceCut.setPaintFlags(groceryPriceCut.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            increase = itemView.findViewById(R.id.btn_increase);
            decrease = itemView.findViewById(R.id.btn_decrease);
            productQTY = itemView.findViewById(R.id.tv_productQTY);
            linearProductDetail = itemView.findViewById(R.id.linearProductList);
        }

        public void increaseInteger(View view) {
            minteger = minteger + 1;
            display(minteger);
        }

        public void decreaseInteger(View view) {
            if (minteger != 0) {
                minteger = minteger - 1;
                display(minteger);
            }
        }

        private void display(int number) {
            TextView displayInteger = (TextView) itemView.findViewById(R.id.tv_integerNumber);
            displayInteger.setText(String.valueOf(number));
        }
    }
}
