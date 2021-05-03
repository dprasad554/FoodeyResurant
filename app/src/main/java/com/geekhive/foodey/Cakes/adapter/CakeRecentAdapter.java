package com.geekhive.foodey.Cakes.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.geekhive.foodey.R;

import java.util.ArrayList;

public class CakeRecentAdapter extends RecyclerView.Adapter<CakeRecentAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    ArrayList<Integer> groceryImages;
    ArrayList<String> producttitle;

    public CakeRecentAdapter(Context context) {
        this.mcontext = context;
        this.groceryImages = groceryImages;
        this.producttitle = producttitle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_layout_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView productImage;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = (ImageView)itemView.findViewById(R.id.iv_vegImage);
            cardView = (CardView)itemView.findViewById(R.id.card_viewMain);
        }
    }
}
