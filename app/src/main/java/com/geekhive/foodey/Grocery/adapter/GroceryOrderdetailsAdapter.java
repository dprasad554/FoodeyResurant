package com.geekhive.foodey.Grocery.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Grocery.beans.groceryhistory.GroceryHistory;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class GroceryOrderdetailsAdapter extends RecyclerView.Adapter<GroceryOrderdetailsAdapter.MyViewHolder> {

    private Context mcontext;
    GroceryHistory history;

    public GroceryOrderdetailsAdapter(Context context, GroceryHistory history) {
        this.mcontext = context;
        this.history = history;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_orderdetials_adapter, parent, false));
    }
    public void onBindViewHolder(final GroceryOrderdetailsAdapter.MyViewHolder holder, final int position) {
        holder.vT_add_name.setText(history.getOrderHistory().getGCartDetail().get(position).get(position).getGrocery().getProductName());
        holder.vT_add_price.setText("Price :"+"₹"+history.getOrderHistory().getGCartDetail().get(position).get(position).getGrocery().getPrice());
        holder.vT_add_decrease.setText(history.getOrderHistory().getGCartDetail().get(position).get(position).getGrocery().getQuantity()+" "+
                history.getOrderHistory().getGCartDetail().get(position).get(position).getGrocery().getQuantityDetail());
        String url = "http://foodeyservices.com/img/grocery/"+
                history.getOrderHistory().getGCartDetail().get(position).get(position).getGrocery().getImage();
        if (!url.equals("")){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.vI_add_veg);
        }
    }

    public int getItemCount() {
        return history.getOrderHistory().getGCartDetail().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
               LinearLayout vL_add_main, vL_add_hide;
        ImageView vI_add_veg;
        TextView vT_add_name, vT_add_price, vT_add_add, vT_add_decrease, vT_add_quantity, vT_add_increase;

        public MyViewHolder(View view) {
            super(view);
            this.vT_add_name = (TextView) view.findViewById(R.id.vT_add_name);
            this.vT_add_price = (TextView) view.findViewById(R.id.vT_add_price);
            this.vT_add_decrease = (TextView) view.findViewById(R.id.vT_add_decrease);
            this.vI_add_veg = (ImageView) view.findViewById(R.id.vI_add_veg);
        }
    }
}