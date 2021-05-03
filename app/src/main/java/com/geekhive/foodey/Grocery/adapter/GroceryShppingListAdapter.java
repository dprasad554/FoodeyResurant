package com.geekhive.foodey.Grocery.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Grocery.beans.groceryproductlist.GrocerySubProductList;
import com.geekhive.foodey.R;

public class GroceryShppingListAdapter extends RecyclerView.Adapter<GroceryShppingListAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    GrocerySubProductList subProductList;
    String quantity = "";

    public GroceryShppingListAdapter(Context context) {
        this.mcontext = context;
        this.subProductList = subProductList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_layout_shoppinglist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
//        holder.vT_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                holder.vL_count.setVisibility(View.VISIBLE);
//                holder.vT_add.setVisibility(View.GONE);
//                int qaunt = Integer.parseInt(holder.qty.getText().toString()) + 1;
//                holder.qty.setText(String.valueOf(qaunt));
//
//                if (Integer.parseInt(holder.qty.getText().toString()) != 0) {
//                    quantity = holder.qty.getText().toString();
//
//                }
//
//                holder.qtyA.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        int qaunt = Integer.parseInt(holder.qty.getText().toString()) + 1;
//                        holder.qty.setText(String.valueOf(qaunt));
//                        quantity = holder.qty.getText().toString();
//                    }
//                });
//
//                    /*holder.vL_count.setVisibility(View.VISIBLE);
//                    holder.add_breakfast.setVisibility(View.GONE);
//                    int qaunt = Integer.parseInt(holder.qty.getText().toString()) + 1;
//                    holder.qty.setText(String.valueOf(qaunt));
//
//                    if (Integer.parseInt(holder.qty.getText().toString()) != 0) {
//                        CallAddService(holder.qty.getText().toString(), snackListOut.getFoodList().get(position).getFood().getResId(), snackListOut.getFoodList().get(position).getFood().getPrice(),
//                                snackListOut.getFoodList().get(position).getFood().getMrp(), snackListOut.getFoodList().get(position).getFood().getId());
//                    } else {
//                        SnackBar.makeText(getActivity(), "Invalid quantity", SnackBar.LENGTH_SHORT).show();
//                    }
//
//                    holder.qtyA.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            int qaunt = Integer.parseInt(holder.qty.getText().toString()) + 1;
//                            holder.qty.setText(String.valueOf(qaunt));
//                            CallAddService(holder.qty.getText().toString(), snackListOut.getFoodList().get(position).getFood().getResId(), snackListOut.getFoodList().get(position).getFood().getPrice(),
//                                    snackListOut.getFoodList().get(position).getFood().getMrp(), snackListOut.getFoodList().get(position).getFood().getId());
//
//
//                        }
//                    });*/
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productQty, originalPrice, savePrice, vT_add, qty;
        ImageView productImage,qtyA;
        CardView vC_adhg_card;
        LinearLayout vL_count;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.tv_productName);
            productQty = itemView.findViewById(R.id.tv_productQTY);
            originalPrice = itemView.findViewById(R.id.tv_productPriceOriginal);
            savePrice = itemView.findViewById(R.id.tv_productPriceSave);
            productImage = itemView.findViewById(R.id.iv_productImage);
            vC_adhg_card = itemView.findViewById(R.id.vC_adhg_card);
            vT_add = itemView.findViewById(R.id.vT_add);
            vL_count = itemView.findViewById(R.id.vL_count);
            qty = itemView.findViewById(R.id.qty);
            qtyA = itemView.findViewById(R.id.qtyA);
        }
    }
}
