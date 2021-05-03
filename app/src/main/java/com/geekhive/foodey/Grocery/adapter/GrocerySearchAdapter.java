package com.geekhive.foodey.Grocery.adapter;

import android.content.Context;
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

import com.geekhive.foodey.Grocery.beans.groceryglobalsearch.GlobalSearch;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class GrocerySearchAdapter extends RecyclerView.Adapter<GrocerySearchAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    GlobalSearch globalSearch;
    int minteger = 0;

    public GrocerySearchAdapter(Context context,GlobalSearch globalSearch ) {
        this.mcontext = context;
        this.globalSearch = globalSearch;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_layout_productlistrecent, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        String url = "http://bado.dsoftveda.com/img/grocery/"+
                globalSearch.getSearch().getStore().get(position).getImage();
        if (!url.equals("")){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.productImage);
        }
//        holder.groceryName.setText(searchDetails.getGrocery().get(position).getGrocery().getProductName());
//        holder.groceryPriceSave.setText("Rs. "+searchDetails.getGrocery().get(position).getGrocery().getPrice());
//        holder.groceryPriceCut.setText("Mrp. "+searchDetails.getGrocery().get(position).getGrocery().getMrp());
//        holder.productQTY.setText("Quantity: "+searchDetails.getGrocery().get(position).getGrocery().getQuantity());
//        holder.linearProductDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mcontext, GroceryDetailActivity.class);
//                intent.putExtra("title",searchDetails.getGrocery().get(position).getGrocery().getProductName());
//                intent.putExtra("id",searchDetails.getGrocery().get(position).getGrocery().getId());
//                intent.putExtra("price",searchDetails.getGrocery().get(position).getGrocery().getPrice());
//                intent.putExtra("description",searchDetails.getGrocery().get(position).getGrocery().getProductDescription());
//                intent.putExtra("mrp",searchDetails.getGrocery().get(position).getGrocery().getMrp());
//                intent.putExtra("quantity",searchDetails.getGrocery().get(position).getGrocery().getQuantity());
//                intent.putExtra("store_id",searchDetails.getGrocery().get(position).getGrocery().getStoreId());
//                intent.putExtra("product_id","");
//                intent.putExtra("url",searchDetails.getGrocery().get(position).getGrocery().getImage());
//
//                mcontext.startActivity(intent);
//            }
//        });

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
        return globalSearch.getSearch().getStore().size();
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
