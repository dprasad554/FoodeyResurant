package com.geekhive.foodey.Grocery.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.R;

import java.util.ArrayList;

public class GroceryPackQtyAdapter extends RecyclerView.Adapter<GroceryPackQtyAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    ArrayList<String> groceryQty;
    ArrayList<String> groceryPrice;

    int minteger = 0;

    public GroceryPackQtyAdapter(Context context, ArrayList<String> groceryQty, ArrayList<String> groceryPrice) {
        this.mcontext = context;
        this.groceryQty = groceryQty;
        this.groceryPrice = groceryPrice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_layout_packsize,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
     holder.qty.setText(groceryQty.get(position));
     holder.price.setText(groceryPrice.get(position));
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
        return groceryQty.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView qty,price;
        LinearLayout linearColor;
        Button increase,decrease;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            qty = (TextView)itemView.findViewById(R.id.tv_pQty);
            price = (TextView)itemView.findViewById(R.id.tv_pPrice);
            linearColor = itemView.findViewById(R.id.linearColor);
            increase = itemView.findViewById(R.id.btn_increase);
            decrease = itemView.findViewById(R.id.btn_decrease);
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
            TextView displayInteger = (TextView)itemView.findViewById(R.id.tv_integerNumber);
            displayInteger.setText(String.valueOf(number));
        }
    }
}
